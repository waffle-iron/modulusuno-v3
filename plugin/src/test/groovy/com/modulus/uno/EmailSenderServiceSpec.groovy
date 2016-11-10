package com.modulus.uno

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(EmailSenderService)
@Mock([Company, User, LoanOrder, PurchaseOrder, Role, UserRole])
class EmailSenderServiceSpec extends Specification {

  def restService = Mock(RestService)
  def companyService = Mock(CompanyService)
  def grailsApplication = new GrailsApplicationMock()

  def setup() {
    service.restService = restService
    service.companyService = companyService
    service.grailsApplication = grailsApplication
  }

  void "should send new client notification"() {
    given:"A legal representative"
      String email = 'user@email.com'
      def profile = Mock(Profile)
      profile.email >> email
      def legalRepresentative = new User(profile:profile)
    and:"A company"
      def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000, legalRepresentatives:[legalRepresentative]).save(validate:false)
    when:"We send notification"
      service.sendNewClientProviderNotificaton(company, 'josdem', EmailerMessageType.CLIENTE)
    then:"We expect notification sent"
    1 * restService.sendCommand(_ as NameCommand, 'clientProvider')
  }

  void "should send sale order to authorize"(){
    given:"A approver"
      String email = 'user@email.com'
      def approver = Mock(User)
      def profile = Mock(Profile)
      approver.profile >> profile
      profile.email >> email
    and:"A company"
      def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000).save()
    and:"A sale order"
      def saleOrder = new SaleOrder(rfc:'rfc', clientName:'clientName', status:SaleOrderStatus.POR_AUTORIZAR, company:company)
    when:"We send notification"
      service.sendSaleOrderToAuthorize(saleOrder, [approver])
    then:"We expect notification sent"
    1 * restService.sendCommand(_ as SaleOrderCommand, 'authorizeSaleOrder')

  }

  void "send notification for Loan Order action"(){
    given: "A Loan Order"
    def loanOrder = prepareLoanOrder()
    when: "Notify the approvement"
    def notifications = service.notifyTheApprovementOfLoanOrder(loanOrder)
    then: "should send notifications"
    notifications[0].message.trim() == "Una Orden de Préstamo ha sido aprobada, haz click en el link de este mail para más información"
    notifications[0].url == grailsApplication.config.grails.server.url + "/loanOrder/show/1"
    notifications[0].nameCompany == loanOrder.company.bussinessName
    notifications[0].emailResponse == loanOrder.creditor.legalRepresentatives[0].profile.email
  }

  private def prepareLoanOrder(){
    def company = new Company(bussinessName:"C1")
    def creditor = new Company(bussinessName:"C2")
    def legalRepresentative = new User(username:"user1")
    def profile = new Profile(email:"some@makingdevs.com")
    legalRepresentative.profile = profile
    creditor.addToLegalRepresentatives(legalRepresentative)
    company.save(validate:false)
    creditor.save(validate:false)
    def loanOrder = new LoanOrder(amount:10000, company:company, creditor:creditor)
    loanOrder.save(validate:false)
    // Aun puede no estar guardadoi
    loanOrder
  }

  void "send notification of Purchase Order to authorize"(){
    given: "A Purchase Order"
    def purchaseOrder = preparePurchaseOrder()
    when: "Send Notificactions to authorizers"
    companyService.getAuthorizersByCompany(_) >> [User.get(1)]
    def notifications = service.sendPurchaseOrderToAuthorize(purchaseOrder)
    then: "should send notifications"
    notifications[0].message.trim() == "Se ha solicitado autorización de una nueva Orden de Pago a Proveedor, si deseas más información haz click en el link de este mail"
    notifications[0].url == grailsApplication.config.grails.server.url + "/purchaseOrder/show/1"
    notifications[0].nameCompany == purchaseOrder.company.bussinessName
    notifications[0].emailResponse == "mailauthorize@mail.com"
  }

  private def preparePurchaseOrder(){
    def company = new Company(bussinessName:"C1")
    def user = new User(username:"authorizer")
    def profile = new Profile(email:"mailauthorize@mail.com")
    user.profile = profile
    user.save(validate:false)
    def role = new Role(authority:"ROLE_INTEGRADO_AUTORIZADOR")
    role.save(validate:false)
    def userRole = new UserRole(user:user, role:role)
    userRole.save(validate:false)
    company.addToActors(user)
    company.save()
    def purchaseOrder = new PurchaseOrder(company:company,providerName:"Proveedor")
    purchaseOrder.save(validate:false)
    purchaseOrder
  }

  void "send notification of Moneyback Order to authorize"(){
    given: "A Purchase Order when isMoneyBackOrder"
    def order = prepareMoneyBackOrder()
    when: "Send Notificactions to authorizers"
    companyService.getAuthorizersByCompany(_) >> [User.get(1)]
    def notifications = service.sendPurchaseOrderToAuthorize(order)
    then: "should send notifications"
    notifications[0].message.trim() == "Se ha solicitado autorización de una nueva Orden de Reembolso, si deseas más información haz click en el link de este mail"
    notifications[0].url == grailsApplication.config.grails.server.url + "/purchaseOrder/show/1"
    notifications[0].nameCompany == order.company.bussinessName
    notifications[0].emailResponse == "mailauthorize@mail.com"
  }

  private def prepareMoneyBackOrder(){
    def company = new Company(bussinessName:"C1")
    def user = new User(username:"authorizer")
    def profile = new Profile(email:"mailauthorize@mail.com")
    user.profile = profile
    user.save(validate:false)
    def role = new Role(authority:"ROLE_INTEGRADO_AUTORIZADOR")
    role.save(validate:false)
    def userRole = new UserRole(user:user, role:role)
    userRole.save(validate:false)
    company.addToActors(user)
    company.save()
    def moneyBackOrder = new PurchaseOrder(company:company,providerName:"Empleado",isMoneyBackOrder:true)
    moneyBackOrder.save(validate:false)
    moneyBackOrder
  }

}
