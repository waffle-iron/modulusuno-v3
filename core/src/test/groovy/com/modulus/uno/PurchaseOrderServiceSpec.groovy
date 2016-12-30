package com.modulus.uno

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(PurchaseOrderService)
@Mock([BankAccount, BusinessEntity, PurchaseOrder, Company, User, Address, Authorization,PaymentToPurchase])
class PurchaseOrderServiceSpec extends Specification {

  def emailSenderService = Mock(EmailSenderService)
  def documentService = Mock(DocumentService)
  def modulusUnoService = Mock(ModulusUnoService)
  def restService = Mock(RestService)

  def setup(){
    service.emailSenderService = emailSenderService
    service.documentService = documentService
    service.modulusUnoService = modulusUnoService
    service.restService = restService
  }

  void "should create a purchase order"() {
    given:"A company"
    def company = new Company(rfc:"JIGE930831NZ1",
                                bussinessName:"Apple Computers",
                                webSite:"http://www.apple.com",
                                employeeNumbers:40,
                                grossAnnualBilling:4000).save(validate:false)
    and:"A business entity"
      def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
      BankAccount bankAccount = new BankAccount().save(validate:false)
      Map params = [providerId:businessEntity.id, bankAccountId:bankAccount.id, fechaPago:new Date().format("dd/MM/yyyy").toString(), externalId:"12345"]
    when:"We create a purchase order"
      def result = service.createPurchaseOrder(company.id, params)
    then:"We expect a new purchase order"
      result instanceof PurchaseOrder
      result.status == PurchaseOrderStatus.CREADA
  }

  void "should send a purchase order to confirmation"(){
  given:"A purchase order"
    def company = new Company(rfc:"JIGE930831NZ1",
                                bussinessName:"Apple Computers",
                                webSite:"http://www.apple.com",
                                employeeNumbers:40,
                                grossAnnualBilling:4000).save(validate:false)
    def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
    BankAccount bankAccount = new BankAccount().save(validate:false)
    Map params = [providerId:businessEntity.id, bankAccountId:bankAccount.id, fechaPago:new Date().format("dd/MM/yyyy").toString(), externalId:"12345"]
    def purchaseOrder = service.createPurchaseOrder(company.id, params)
  when:"We authorize a purchase order"
    def result = service.requestAuthorizationForTheOrder(purchaseOrder)
  then:"We expect new status"
    result.status == PurchaseOrderStatus.POR_AUTORIZAR
    1 * emailSenderService.sendPurchaseOrderToAuthorize(purchaseOrder)
    result instanceof PurchaseOrder
  }

  void "verify if exist  or not original date in purchaseOrder"() {
    given:
      def purchaseOrder = new PurchaseOrder()
      purchaseOrder.providerName = "prueba"
      purchaseOrder.fechaPago = fechaPago
      purchaseOrder.originalDate = originalDate
      purchaseOrder.save(validate:false)
    when:
      def purchaseORderResult = service.updateDatePaymentForOrder(1, sendDate)
    then:
      purchaseORderResult.originalDate != null
      purchaseORderResult.fechaPago == sendDate
   where:
      fechaPago     | originalDate  | sendDate      | fechaPagoOriginal
      new Date()    | null          | new Date()+5  | new Date()
      new Date()+1  | new Date()+17 | new Date()+17 | new Date()+17

  }

  void "verify purchase order had one payment"() {
    given:
      def purchaseOrder = new PurchaseOrder()
      purchaseOrder.providerName = "prueba"
      purchaseOrder.save(validate:false)
    and:
      def payment = new PaymentToPurchase().save(validate:false)
    when:
      def purchaseOrderResult = service.addingPaymentToPurchaseOrder(payment, purchaseOrder)
    then:
      purchaseOrderResult.payments.size() == 1
  }

  void "verify if purchase order had multi payments"() {
    given:
      def purchaseOrder = new PurchaseOrder()
      purchaseOrder.providerName = "prueba"
      purchaseOrder.save(validate:false)
    and:
      def paymentList = createMultiPayments(numberPayments)
    when:
      paymentList.each { payment ->
        service.addingPaymentToPurchaseOrder(payment,purchaseOrder)
      }
    then:
      purchaseOrder.payments.size() == numberPayments
    where:
      numberPayments || havePayments
        1            || true
        2            || true
        3            || true
  }

  private def createMultiPayments(def numberPayments) {
    def payments = []
   (1..numberPayments).each {
     payments.add(new PaymentToPurchase().save(validate:false))
   }
   payments
  }



}
