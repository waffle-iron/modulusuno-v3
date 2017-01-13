package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class LoanOrderController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  LoanOrderService loanOrderService
  def springSecurityService
  def companyService
  def emailSenderService

  def acceptLoanOrder(LoanOrder loanOrder){
    loanOrder.status = LoanOrderStatus.ACCEPTED
    emailSenderService.notifyLoanOrderChangeStatus(loanOrder)
    respond view:'list', LoanOrder.findAllByCreditorAndStatus(loanOrder.company, LoanOrderStatus.APPROVED)
  }

  def addDocumentToLoanOrder(LoanOrder loanOrder){
    loanOrderService.addDocumentToLoanOrder(params.loanOrderDocument, loanOrder, 'loanOrder')
    redirect action:"show", id:loanOrder.id, params:params
  }

  def authorize(LoanOrder loanOrder){
    loanOrderService.addAuthorizationToLoanOrder(loanOrder, springSecurityService.currentUser)
    redirect action:'index'
  }

  def authorizeLoanOrder(LoanOrder loanOrder) {
    if (companyService.enoughBalanceCompany(loanOrder.company, loanOrder.amount)){
      loanOrderService.addAuthorizationToLoanOrder(loanOrder, springSecurityService.currentUser)
      redirect action:'listToAuthorize'
      return
    } else {
      flash.message = message(code:'company.insufficient.balance')
      redirect action:'show', id:loanOrder.id
    }
  }

  def executeLoanOrder(LoanOrder loanOrder) {
    String messageSuccess = message(code:"loanOrder.already.executed")
    try {
      if (loanOrderIsInStatus(loanOrder, LoanOrderStatus.ACCEPTED)) {
        loanOrderService.executeLoanOrder(loanOrder)
        messageSuccess = message(code:"loanOrder.executed.message")
      }
    } catch (CommissionException e) {
      flash.message = e.message
      redirect action:'show', id:loanOrder.id
      return
    }
    redirect action:'listToExecute', params:[messageSuccess:messageSuccess]
  }

  private Boolean loanOrderIsInStatus(LoanOrder order, def statusExpected) {
    order.status == statusExpected
  }

  def approveLoanOrder(LoanOrder loanOrder) {
    loanOrderService.approvingLoanOrder(loanOrder)

    redirect action:'listToApprove'
  }

  def cancelLoanOrder(LoanOrder loanOrder){
    loanOrder.status = LoanOrderStatus.CANCELED
    loanOrder.save flush:true
    emailSenderService.notifyLoanOrderChangeStatus(loanOrder)

    redirect action:'listToAuthorize'
  }

  def confirmLoanOrder(LoanOrder loanOrder){
    loanOrderService.sendToConfirmLoanOrder(loanOrder)

    redirect action:'list'
  }

  def create(){
    Company company = Company.get(session.company)
    respond new LoanOrder(params), model:[companies:Company.findAllByStatusAndIdNotEqual(CompanyStatus.ACCEPTED, company.id)]
  }

  def edit(LoanOrder loanOrder) {
    Company company = Company.get(session.company)
    respond loanOrder, model:[companies:Company.findAllByStatusAndIdNotEqual(CompanyStatus.ACCEPTED, company.id)]
  }

  def index() {
    respond LoanOrder.list()
  }

  def list(){
    def company = Company.get(session.company ?: params.companyId)
    respond LoanOrder.findAllByCompany(company)
  }

  def listApproved(){
    def company = Company.get(session.company)
    respond view:'list', LoanOrder.findAllByCreditorAndStatus(company, LoanOrderStatus.APPROVED)
  }

  def listToAuthorize(){
    Company company = Company.get(session.company)
    def orders = LoanOrder.findAllByCompanyAndStatus(company, LoanOrderStatus.VALIDATE)
    render view:'list', model:[loanOrderList:orders]
  }

  def listToApprove(){
    render view:'list', model:[loanOrderList:LoanOrder.findAllByStatus(LoanOrderStatus.AUTHORIZED)]
  }

  def listToExecute(){
    render view:'list', model:[loanOrderList:LoanOrder.findAllByStatus(LoanOrderStatus.ACCEPTED), messageSuccess:params.messageSuccess]
  }

  def rejectLoanOrder(LoanOrder loanOrder){
    loanOrder.status = LoanOrderStatus.REJECTED
    loanOrder.save flush:true
    emailSenderService.notifyLoanOrderChangeStatus(loanOrder)

    redirect action:'listToApprove'
  }

  @Transactional
  def save(LoanOrder loanOrder){
    Company company = Company.get(session.company)
    loanOrder.company = company
    loanOrder.amount = new BigDecimal(params.amount)
    loanOrder.rate = new BigDecimal(params.rate)

    if (!loanOrder.validate()){
      transactionStatus.setRollbackOnly()
      render view:'create', model:[loanOrder:loanOrder, companies:Company.findAllByStatusAndIdNotEqual(CompanyStatus.ACCEPTED, company.id)]
      return
    }

    loanOrder.save flush:true

    redirect action:'show', id:loanOrder.id
  }

  def show(LoanOrder loanOrder){
    respond loanOrder, model:[user:springSecurityService.currentUser, baseUrlDocuments:grailsApplication.config.grails.url.base.images]
  }

  @Transactional
  def update(LoanOrder loanOrder){
    loanOrder.amount = new BigDecimal(params.amount)
    loanOrder.rate = new BigDecimal(params.rate)

    if (!loanOrder.validate()){
      transactionStatus.setRollbackOnly()
      render view:'edit', model:[loanOrder:loanOrder, companies:Company.findAllByStatusAndIdNotEqual(CompanyStatus.ACCEPTED, loanOrder.company.id)]
      return
    }

    loanOrder.save flush:true

    redirect action:'show', id:loanOrder.id
  }

}
