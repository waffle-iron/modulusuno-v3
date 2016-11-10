package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class DepositOrderService {

  def grailsApplication
  def restService
  def documentService
  def modulusUnoService
  def companyService
  def userService

  def addAuthorizationToDepositOrder(DepositOrder order, User user) {
    def authorization = new Authorization(user:user)
    order.addToAuthorizations(authorization)
    order.save()
  }

  def executeDepositOrder(DepositOrder order){
    def cashinResult = modulusUnoService.generateACashinForIntegrated(order)
    order.status = DepositOrderStatus.EXECUTED
    order.uuidTransaction = cashinResult.str
    order.save()
    notifyDepositOrderWasExecuted(order)
  }

  def getDepositOrderStatus(String status){
    def listDepositOrderStatus = []
    listDepositOrderStatus = Arrays.asList(DepositOrderStatus.values())
    if (status){
      def listStatus = status.tokenize(",")
      listDepositOrderStatus = listStatus.collect { it as DepositOrderStatus }
    }

    listDepositOrderStatus
  }

  def getDepositOrdersToList(Long company, params){
    def statusOrders = getDepositOrderStatus(params.status)
    def depositOrders = [:]
    if (company){
      depositOrders.list = DepositOrder.findAllByCompanyAndStatusInList(Company.get(company), statusOrders, params)
      depositOrders.items = DepositOrder.countByCompanyAndStatusInList(Company.get(company), statusOrders)
    } else {
      depositOrders.list = DepositOrder.findAllByStatusInList(statusOrders, params)
      depositOrders.items = DepositOrder.countByStatusInList(statusOrders)
    }
    depositOrders
  }

  def getTotalDepositOrderAuthorizedOfCompany(Company company){
    DepositOrder.findAllByCompanyAndStatus(company, DepositOrderStatus.AUTHORIZED).amount.sum()
  }

  def isFullAuthorized(DepositOrder order){
    (order.authorizations?.size() ?: 0) >= order.company.numberOfAuthorizations
  }

  @Transactional(readOnly = true)
  def notifyAuthorizationDepositOrder(DepositOrder order){
    def usersToNotify = order.company.legalRepresentatives
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    usersToNotify.addAll(authorizers)
    def usersAdminIecce = userService.getUsersByRole("ROLE_ADMIN_IECCE")
    usersToNotify.addAll(usersAdminIecce)
    def usersOperatorsIecce = userService.getUsersByRole("ROLE_OPERADOR_IECCE")
    usersToNotify.addAll(usersOperatorsIecce)
    usersToNotify.each(){
      def messageCommand = notificationDepositOrderToValidate(order, it.profile.email)
      messageCommand.message = "Una Orden de Depósito ha sido autorizada, haz click en el link de este correo para más información"
      restService.sendCommand(messageCommand, grailsApplication.config.emailer.notificationIntegrated)
    }
  }

  def notifyDepositOrderWasExecuted(DepositOrder order){
    order.company.legalRepresentatives.each(){
      def messageCommand = notificationDepositOrderToValidate(order, it.profile.email)
      messageCommand.message = "Una Orden de Depósito ha sido abonada a la cuenta de la compañía, haz click en el link de este correo para más información"
      restService.sendCommand(messageCommand, grailsApplication.config.emailer.notificationIntegrated)
    }
  }

  def uploadDepositSlipToOrder(def document,DepositOrder order,String type) {
    documentService.uploadDocumentForOrder(document,type,order)
    sendNotificationOfDepositOrderToAuthorize(order)
    order.status = DepositOrderStatus.VALIDATE
    order.save()
  }

  private def notificationDepositOrderToValidate(DepositOrder order,def email) {
    def emailNotificationCommand = new EmailNotificationToIntegratedCommand()
    emailNotificationCommand.emailResponse = email
    emailNotificationCommand.nameCompany = order.company.toString()
    emailNotificationCommand.message = "Se ha solicitado autorización de una nueva Orden de Depósito, si deseas más información haz click en el link de este mail"
    emailNotificationCommand.url = "${grailsApplication.config.grails.server.url}/depositOrder/show/${order.id}"
    emailNotificationCommand
  }

  private def sendNotificationOfDepositOrderToAuthorize(DepositOrder order) {
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    authorizers.each(){
      def messageCommand = notificationDepositOrderToValidate(order,it.profile.email)
      restService.sendCommand(messageCommand, grailsApplication.config.emailer.notificationIntegrated)
    }
  }


}
