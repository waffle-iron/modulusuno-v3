package com.modulus.uno

import grails.transaction.Transactional

@Transactional(readOnly=true)
class EmailSenderService {

  def restService
  def grailsApplication
  def companyService
  def userService
  CorporateService corporateService
  DirectorService directorService

  static final messages = [
    SaleOrder : [:],
    PurchaseOrder : [
      sendToAuthorize:"""\
        Se ha solicitado autorización de una nueva Orden de Pago a Proveedor, si deseas más información haz click en el link de este mail
      """,
      alreadyAuthorize:"""\
        Una Orden de Pago a Proveedor ha sido autorizada, haz click en el link de este mail para más información
      """,
      paymentOrder:"""\
        Una Orden de Pago a Proveedor ha sido PAGADA, haz click en el link de este mail para más información
      """
    ],
    MoneyBackOrder : [
      sendToAuthorize:"""\
        Se ha solicitado autorización de una nueva Orden de Reembolso, si deseas más información haz click en el link de este mail
      """,
      alreadyAuthorize:"""\
        Una Orden de Reembolso ha sido autorizada, haz click en el link de este mail para más información
      """,
      paymentOrder:"""\
        Una Orden de Reembolso ha sido PAGADA, haz click en el link de este mail para más información
      """
    ],
    LoanOrder : [
      sendToAuthorize:"""\
        Se ha solicitado autorización de una nueva Orden de Préstamo, si deseas más información haz click en el link de este mail
      """,
      alreadyAuthorize:"""\
        Una Orden de Préstamo ha sido autorizada, haz click en el link de este mail para más información
      """,
      approvementAuthorize:"""\
        Una Orden de Préstamo ha sido aprobada, haz click en el link de este mail para más información
      """
    ],
    LoanPaymentOrder : [
      sendToAuthorize:"""\
        Se ha solicitado autorización de una nueva Orden Pago a Préstamo, si deseas más información haz click en el link de este mail
      """,
      alreadyAuthorize:"""\
        Una Orden de Pago a Préstamo ha sido autorizada, haz click en el link de este mail para más información
      """,
      paymentOrder:"""\
        Una Orden de Pago a Préstamo ha sido EJECUTADA, haz click en el link de este mail para más información
      """
    ],
    FeesReceipt : [
      sendToAuthorize:"""\
        Se ha solicitado autorización de una nueva Orden de Recibo de Honorarios, si deseas más información haz click en el link de este mail
      """,
      alreadyAuthorize:"""\
        Una Orden de Recibo de Honorarios ha sido autorizada, haz click en el link de este mail para más información
      """,
      paymentOrder:"""\
        Una Orden de Recibo de Honorarios ha sido PAGADA, haz click en el link de este mail para más información
      """
    ]
  ]

  static final urls = [
    LoanOrder :"/loanOrder/show/",
    PurchaseOrder : "/purchaseOrder/show/",
    SaleOrder : "",
    FeesReceipt : "/feesReceipt/show/",
    LoanPaymentOrder : "/loanPaymentOrder/show/"
  ]

  def authorizeLoanPaymentOrder (LoanPaymentOrder loanPaymentOrder) {
    prepareCommandsAndSendRequestToEmailer(getUsersToNotify(loanPaymentOrder.company, "alreadyAuthorize"), loanPaymentOrder, "alreadyAuthorize")
  }

  def emailIntegratedCommand(String message, String url, String nameCompany, def email){
    def emailCommand = new EmailNotificationToIntegratedCommand()
    emailCommand.emailResponse = email
    emailCommand.nameCompany = nameCompany
    emailCommand.message = message
    emailCommand.url = grailsApplication.config.grails.server.url + url
    emailCommand
  }

  private def prepareCommandsAndSendRequestToEmailer(usersToNotify, order, actionToExecute){
    String classMessage = order.class.simpleName == "PurchaseOrder" && order.isMoneyBackOrder ? "MoneyBackOrder" : order.class.simpleName
    usersToNotify.collect { user ->
      def command = emailIntegratedCommand(messages."${classMessage}"."${actionToExecute}",urls."${order.class.simpleName}" + order.id, order.company.bussinessName, user.profile.email)
      restService.sendCommand(command, grailsApplication.config.emailer.notificationIntegrated)
      command
    }
  }

  private List<User> getUsersToNotify(Company company, String actionToExecute){
    List<User> users = []
    switch (actionToExecute) {
      case "sendToAuthorize" :
          users.addAll(companyService.getAuthorizersByCompany(company))
        break
      case "alreadyAuthorize" :
          users.addAll(company.legalRepresentatives.collect())
          users.addAll(companyService.getAuthorizersByCompany(company))
          users.addAll(userService.getUsersByRole("ROLE_ADMIN_IECCE"))
          users.addAll(userService.getUsersByRole("ROLE_OPERADOR_IECCE"))
        break
      case "approvementAuthorize" : case "paymentOrder" :
          users.addAll(company.legalRepresentatives.collect())
        break
    }
    users
  }

  def notifyTheApprovementOfLoanOrder(LoanOrder order){
    ArrayList<User> usersToNotify = directorService.findUsersOfCompanyByRole(order.creditor.id,'ROLE_LEGAL_REPRESENTATIVE_VISOR') ?: []
    usersToNotify += (directorService.findUsersOfCompanyByRole(order.creditor.id,'ROLE_LEGAL_REPRESENTATIVE_EJECUTOR') ?: [])
    usersToNotify = usersToNotify.unique()
    prepareCommandsAndSendRequestToEmailer(usersToNotify, order, "approvementAuthorize")
  }

  // TODO: Revisión pra refactor en el agrupamiento de la gente
  def notifyAuthorizationLoanOrder(LoanOrder order){
    def usersToNotify = order.company.legalRepresentatives.collect()
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    usersToNotify.addAll(authorizers)
    def usersAdminIecce = userService.getUsersByRole("ROLE_ADMIN_IECCE")
    usersToNotify.addAll(usersAdminIecce)
    def usersOperatorsIecce = userService.getUsersByRole("ROLE_OPERADOR_IECCE")
    usersToNotify.addAll(usersOperatorsIecce)
    prepareCommandsAndSendRequestToEmailer(usersToNotify, order, "alreadyAuthorize")
  }

  def notifyLoanPaymentOrderExecuted(LoanPaymentOrder loanPaymentOrder) {
    prepareCommandsAndSendRequestToEmailer(getUsersToNotify(loanPaymentOrder.company, "paymentOrder"), loanPaymentOrder, "paymentOrder")
  }

  void notifyAcceptedCompany(Company company) {
    String url = "${grailsApplication.config.accepting.integrated}${company.id}"
    String message = "La Empresa ${company}, ha sido aprobada para formar parte de de ModulusUno"
    def email = company.actors.first().profile.email
    def command = emailIntegratedCommand(message, url, company.toString(), email)
    restService.sendCommand(command, grailsApplication.config.emailer.notificationIntegrated)
  }

  void notifyRejectedCompany(Company company) {
    String url = "${grailsApplication.config.rejected.integrated}${company.id}"
    String message = "La Empresa ${company}, ha sido rechazada por diversos motivos"
    User user = corporateService.findCorporateUserOfCompany(company.id)
    def email = user.profile.email
    def command = emailIntegratedCommand(message, url, company.toString(), email)
    restService.sendCommand(command, grailsApplication.config.emailer.notificationIntegrated)
  }

  def sendFeesReceiptAuthorized(FeesReceipt feesReceipt) {
    List<User> usersToNotify = getUsersToNotify(feesReceipt.company, "alreadyAuthorize")
    prepareCommandsAndSendRequestToEmailer(usersToNotify, feesReceipt, "alreadyAuthorize")
  }

  def sendFeesReceiptToAuthorize(FeesReceipt feesReceipt) {
    prepareCommandsAndSendRequestToEmailer(getUsersToNotify(feesReceipt.company, "sendToAuthorize"), feesReceipt, "sendToAuthorize")
  }

  def sendLoanOrderToAuthorize(LoanOrder order){
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    prepareCommandsAndSendRequestToEmailer(authorizers, order, "sendToAuthorize")
  }

  def sendLoanPaymentOrderToAuthorize(LoanPaymentOrder loanPaymentOrder){
    prepareCommandsAndSendRequestToEmailer(getUsersToNotify(loanPaymentOrder.company, "sendToAuthorize"), loanPaymentOrder, "sendToAuthorize")
  }

  def sendNewClientProviderNotificaton(Company company, String name, EmailerMessageType type) {
    company.legalRepresentatives.each { legal ->
      def message = new NameCommand(email:legal.profile.email, name:name, company:company.bussinessName, type:type)
      restService.sendCommand(message, grailsApplication.config.emailer.clientProvider)
    }
  }

  def sendPaidPurchaseOrder(PurchaseOrder order){
    order.company.legalRepresentatives.each(){
      def command = emailIntegratedCommand("Una Orden de Pago a Proveedor ha sido PAGADA, haz click en el link de este mail para más información","/purchaseOrder/show/${order.id}", order.company.toString(), it.profile.email)
      restService.sendCommand(command, grailsApplication.config.emailer.notificationIntegrated)
    }
  }

  def sendPurchaseOrderAuthorized(PurchaseOrder order){
    def usersToNotify = order.company.legalRepresentatives.collect()
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    usersToNotify.addAll(authorizers)
    def usersAdminIecce = userService.getUsersByRole("ROLE_ADMIN_IECCE")
    usersToNotify.addAll(usersAdminIecce)
    def usersOperatorsIecce = userService.getUsersByRole("ROLE_OPERADOR_IECCE")
    usersToNotify.addAll(usersOperatorsIecce)
    prepareCommandsAndSendRequestToEmailer(usersToNotify, order, "alreadyAuthorize")
  }

  def sendPurchaseOrderToAuthorize(PurchaseOrder order){
    def authorizers = companyService.getAuthorizersByCompany(order.company)
    prepareCommandsAndSendRequestToEmailer(authorizers, order, "sendToAuthorize")
 }

  def sendSaleOrderToAuthorize(saleOrder, users){
    users.each { user ->
      def command = new SaleOrderCommand(email:user.profile.email, rfc:saleOrder.rfc, name: saleOrder.clientName, url:"${grailsApplication.config.grails.server.url}/saleOrder/show/${saleOrder.id}")
      restService.sendCommand(command, grailsApplication.config.emailer.authorizeSaleOrder)
    }
  }

}
