package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LoanPaymentOrderController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def loanOrderHelperService
    def springSecurityService
    def companyService

    @Transactional
    def authorizeLoanPaymentOrder(LoanPaymentOrder loanPaymentOrder) {
      if (companyService.enoughBalanceCompany(loanPaymentOrder.company, loanPaymentOrder.totalPayment)){
        User user = springSecurityService.currentUser
        loanOrderHelperService.addAuthorizationToLoanPaymentOrder(loanPaymentOrder, user)
        if (loanOrderHelperService.loanPaymentIsFullAuthorized(loanPaymentOrder)) {
          loanOrderHelperService.authorizeLoanPaymentOrder(loanPaymentOrder)
        }
        redirect action:'listToAuthorize'
        return
      } else {
        flash.message = message(code:'company.insufficient.balance')
        redirect action:'show', id:loanPaymentOrder.id
      }
    }

    @Transactional
    def cancelLoanPaymentOrder(LoanPaymentOrder loanPaymentOrder) {
      loanPaymentOrder.status = LoanPaymentOrderStatus.CANCELED
      loanPaymentOrder.save flush:true

      redirect action:'listToAuthorize'
    }

    def chooseLoan() {
      Company company = Company.get(session.company)
      List<LoanOrder> loanOrderList = LoanOrder.findAllByCreditorAndStatus(company, LoanOrderStatus.EXECUTED)
      render view:"listLoans", model:[loanOrderList:loanOrderList, payments:params.payments]
    }

    def create() {
      LoanOrder loanOrder = LoanOrder.get(params.idLoanOrder)
      if (loanOrderHelperService.loanPaymentOrderInProcess(loanOrder)){
        flash.message = "El préstamo seleccionado tiene un pago en proceso"
        redirect action:'chooseLoan'
        return
      }
      LoanPaymentOrder loanPaymentOrder = loanOrderHelperService.computeMinimumPayment(loanOrder)
      respond loanPaymentOrder, model:[lastDatePayment: loanOrderHelperService.getLastDatePayment(loanOrder), currentBalance: loanOrderHelperService.getCurrentBalance(loanOrder)]
    }

    def edit(LoanPaymentOrder loanPaymentOrder) {
        respond loanPaymentOrder
    }

    @Transactional
    def executeLoanPaymentOrder(LoanPaymentOrder loanPaymentOrder) {
      String messageSuccess = message(code:"loanPaymentOrder.already.executed")
      try {
        if (loanOrderPaymentIsInStatus(loanPaymentOrder, LoanPaymentOrderStatus.AUTHORIZED)){
          loanOrderHelperService.executeLoanPaymentOrder(loanPaymentOrder)
          messageSuccess = message(code:"loanPaymentOrder.executed.message")
        }
      } catch (CommissionException e) {
        flash.message = e.message
        redirect action:'show', id:loanPaymentOrder.id
        return
      }
      redirect action:'listToExecute', params:[messageSuccess:messageSuccess]
    }

    private Boolean loanOrderPaymentIsInStatus(LoanPaymentOrder loanPaymentOrder, def statusExpected) {
      loanPaymentOrder.status == statusExpected
    }

    def listAll() {
      params.max = 10
      render view:'list', model:[loanPaymentOrderList:LoanPaymentOrder.list(params), loanPaymentOrderCount:LoanPaymentOrder.count()]
    }

    def listToAuthorize() {
      Company company = Company.get(session.company)
      params.max = 10
      List<LoanPaymentOrder> loanPaymentOrderList = LoanPaymentOrder.findAllByCompanyAndStatus(company, LoanPaymentOrderStatus.VALIDATE, params)
      Integer loanPaymentOrderCount = LoanPaymentOrder.countByCompanyAndStatus(company, LoanPaymentOrderStatus.VALIDATE)
      render view:'list', model:[loanPaymentOrderList:loanPaymentOrderList, loanPaymentOrderCount:loanPaymentOrderCount]
    }

    def listToExecute() {
      params.max = 10
      List<LoanPaymentOrder> loanPaymentOrderList = LoanPaymentOrder.findAllByStatus(LoanPaymentOrderStatus.AUTHORIZED, params)
      Integer loanPaymentOrderCount = LoanPaymentOrder.countByStatus(LoanPaymentOrderStatus.AUTHORIZED)
      render view:'list', model:[loanPaymentOrderList:loanPaymentOrderList, loanPaymentOrderCount:loanPaymentOrderCount, messageSuccess:params.messageSuccess]
    }

    def loanOrderPayments (LoanOrder loanOrder) {
      params.max = 10
      List<LoanPaymentOrder> loanPaymentOrderList = LoanPaymentOrder.findAllByLoanOrder(loanOrder, params)
      Integer loanPaymentOrderCount = LoanPaymentOrder.countByLoanOrder(loanOrder)
      render view:'list', model:[loanPaymentOrderList:loanPaymentOrderList, loanPaymentOrderCount:loanPaymentOrderCount]
    }

    @Transactional
    def rejectLoanPaymentOrder (LoanPaymentOrder loanPaymentOrder) {
      loanPaymentOrder.status = LoanPaymentOrderStatus.REJECTED
      loanPaymentOrder.save flush:true
      redirect action:'listToExecute'
    }

    @Transactional
    def requestAuthorization(LoanPaymentOrder loanPaymentOrder) {
      loanOrderHelperService.sendToAuthorize(loanPaymentOrder)
      redirect action:'loanOrderPayments', id:loanPaymentOrder.loanOrder.id
    }

    @Transactional
    def save(LoanPaymentOrder loanPaymentOrder) {
        loanPaymentOrder.amountInterest = new BigDecimal(params.amountInterest)
        loanPaymentOrder.amountIvaInterest = new BigDecimal(params.amountIvaInterest)
        loanPaymentOrder.amountToCapital = new BigDecimal(params.amountToCapital)
        loanPaymentOrder.company = Company.get(session.company)

        if (loanPaymentOrder == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (loanPaymentOrder.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond loanPaymentOrder.errors, view:'create', model:[lastDatePayment: loanOrderHelperService.getLastDatePayment(loanOrder), currentBalance: loanOrderHelperService.getCurrentBalance(loanOrder)]
            return
        }

        loanPaymentOrder.save flush:true

        redirect action:'show', id:loanPaymentOrder.id
    }

    def show(LoanPaymentOrder loanPaymentOrder) {
      BigDecimal currentBalance = loanOrderHelperService.getCurrentBalance(loanPaymentOrder.loanOrder)
      respond loanPaymentOrder, model:[currentBalance:currentBalance, user:springSecurityService.currentUser]
    }

}
