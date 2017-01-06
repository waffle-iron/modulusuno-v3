package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class DepositOrderController {

    def springSecurityService
    def depositOrderService
    def documentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    // TODO: Refactor
    def authorizeDepositOrder(DepositOrder order) {
      def user = springSecurityService.currentUser
      depositOrderService.addAuthorizationToDepositOrder(order, user)
      if (depositOrderService.isFullAuthorized(order)){
        order.status = DepositOrderStatus.AUTHORIZED
        order.save flush:true
        depositOrderService.notifyAuthorizationDepositOrder(order)
      }

      redirect action:"list", params:[status: params.status]

    }

    def cancelDepositOrder(DepositOrder order){
      order.status = DepositOrderStatus.CANCELED
      order.save flush:true

      redirect action:"list", params:[status:'VALIDATE']
    }

    def conciliateDepositOrder(DepositOrder order){
      order.status = DepositOrderStatus.CONCILIATED
      order.save flush:true

      redirect action:"list", params:[status:"EXECUTED"]
    }

    def confirmDepositOrder(DepositOrder order){
      if (order.status != DepositOrderStatus.CREATED )
        redirect action:"show",id:order.id
      render view:"confirm", model:[depositOrder:order]
    }

    def create() {
      respond new DepositOrder(params)
    }

    def executeDepositOrder(DepositOrder order){
      String messageSuccess = message(code:"depositOrder.already.executed")
      try {
        if (order.status == DepositOrderStatus.AUTHORIZED) {
          depositOrderService.executeDepositOrder(order)
          messageSuccess = message(code:"depositOrder.executed.message")
        }
      } catch (CommissionException e) {
        flash.message = e.message
        redirect action:'show', id:order.id, params:[status:'AUTHORIZED']
        return
      }
      redirect action:"list", params:[status: params.status, messageSuccess:messageSuccess]
    }

    @Transactional
    def delete(DepositOrder depositOrder) {

      if (depositOrder == null) {
        transactionStatus.setRollbackOnly()
        notFound()
        return
      }

      depositOrder.delete flush:true

      request.withFormat {
        form multipartForm {
          flash.message = message(code: 'default.deleted.message', args: [message(code: 'depositOrder.label', default: 'DepositOrder'), depositOrder.id])
          redirect action:"index", method:"GET"
        }
        '*'{ render status: NO_CONTENT }
      }
    }

    def deleteOrder(DepositOrder depositOrder) {
      depositOrder.delete flush:true
      redirect action:"list"
    }

    def documentDepositOrder() {
      def document = params.depositSlip
      def order = DepositOrder.findById(params.depositOrderId)
      depositOrderService.uploadDepositSlipToOrder(document,order,"depositSlip")
      redirect action:"list"
    }

    def edit(DepositOrder depositOrder) {
      respond depositOrder
    }

    def index(Integer max) {
      def roles = springSecurityService.getPrincipal().getAuthorities()
      def company = Company.findById(session.company.toLong())
      def depositOrder = DepositOrder.findAllByCompany(company)
      [depositOrderCount: depositOrder.size(),depositOrderList:depositOrder]
    }

    def list() {
      params.max = params.max ?: 10
      def depositOrders =[:]
      depositOrders = depositOrderService.getDepositOrdersToList(session.company ? session.company.toLong(): session.company, params)

      [depositOrder: depositOrders.list, depositOrderCount: depositOrders.items]
    }

    protected void notFound() {
      request.withFormat {
        form multipartForm {
          flash.message = message(code: 'default.not.found.message', args: [message(code: 'depositOrder.label', default: 'DepositOrder'), params.id])
          redirect action: "index", method: "GET"
        }
        '*'{ render status: NOT_FOUND }
      }
    }

    def rejectDepositOrder(DepositOrder order){
      order.status = DepositOrderStatus.REJECTED
      order.save flush:true

      redirect action:"list", params:[status:'AUTHORIZED']
    }

    @Transactional
    def save(DepositOrder depositOrder) {
      def company = Company.get(session.company)
      depositOrder.company = company
      depositOrder.amount = new BigDecimal(params.amount)

      if (depositOrder == null) {
        transactionStatus.setRollbackOnly()
        notFound()
        return
      }

      if (!depositOrder.validate()){
        transactionStatus.setRollbackOnly()
        render view:"create", model:[depositOrder:depositOrder]
        return
      }

      depositOrder.save flush:true

      redirect action:"show",id:depositOrder.id,params:[companyId:company.id]
    }

    def show(DepositOrder depositOrder) {
      User user = springSecurityService.currentUser
      respond depositOrder,model:[companyId:session.company, status:params.status, user:user, baseUrlDocuments:grailsApplication.config.grails.url.base.images]
    }

    @Transactional
    def update(DepositOrder depositOrder) {
      if (depositOrder == null) {
        transactionStatus.setRollbackOnly()
        notFound()
        return
      }

      if (depositOrder.hasErrors()) {
        transactionStatus.setRollbackOnly()
        respond depositOrder.errors, view:'edit'
        return
      }

      depositOrder.save flush:true

      request.withFormat {
        form multipartForm {
          flash.message = message(code: 'default.updated.message', args: [message(code: 'depositOrder.label', default: 'DepositOrder'), depositOrder.id])
          redirect depositOrder
        }
        '*'{ respond depositOrder, [status: OK] }
      }
    }

}
