package com.modulus.uno

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import spock.lang.Ignore

@TestFor(SaleOrderService)
@Mock([BusinessEntity, SaleOrder, Company, User, Address,Authorization, Commission])
class SaleOrderServiceSpec extends Specification {

  def items = []
  def emailSenderService = Mock(EmailSenderService)
  def invoiceService = Mock(InvoiceService)
  def companyService = Mock(CompanyService)

  def setup(){
    items.removeAll()
    service.emailSenderService = emailSenderService
    service.invoiceService = invoiceService
    service.companyService = companyService
  }

  void "should create an sale order"() {
    given:"A company"
    def company = new Company(rfc:"JIGE930831NZ1",
                                bussinessName:"Apple Computers",
                                webSite:"http://www.apple.com",
                                employeeNumbers:40,
                                grossAnnualBilling:4000).save(validate:false)
    and:"A business entity"
      def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
    when:"We create a sale order"
      def result = service.createSaleOrder(businessEntity, company, new Date().format("dd/MM/yyyy").toString())
    then:"We expect a new sale order"
      result instanceof SaleOrder
      result.status == SaleOrderStatus.CREADA
  }

  void "should add an item to a sale order"() {
    given:"A sale order item"
      def item = new SaleOrderItem(sku:'A001',name:'Gazelle A25',price:new BigDecimal(0.0), ieps:new BigDecimal(0.0), iva:new BigDecimal(0.0), unitType:UnitType.UNIDADES, currencyType:CurrencyType.PESOS)
    and:"A sale order"
      def saleOrder = Mock(SaleOrder)
      saleOrder.metaClass.addToItems {
        items.add(item)
      }
    when:"We create a sale order"
      def result = service.addItemToSaleOrder(saleOrder, item)
    then:"We expect a new sale order"
    items.size() == 1
    items.get(0) == item
    1 * saleOrder.save()
  }

  void "should add two items to a sale order"() {
    given:"A sale order item"
      def item1 = new SaleOrderItem(sku:'A001',name:'Gazelle A25',price:new BigDecimal(15000.00), ieps:new BigDecimal(0.0), iva:new BigDecimal(0.0), unitType:UnitType.UNIDADES, currencyType:CurrencyType.PESOS,quantity:0.23)
      def item2 = new SaleOrderItem(sku:'A002',name:'Lemur 14',price:new BigDecimal(0.0), ieps:new BigDecimal(0.0), iva:new BigDecimal(0.0), unitType:UnitType.UNIDADES, currencyType:CurrencyType.PESOS,quantity:1.23)
    and:"A sale order"
      def saleOrder = Mock(SaleOrder)
      saleOrder.metaClass.addToItems {
        items.add(item)
      }
    when:"We create a sale order"
      def result = service.addItemToSaleOrder(saleOrder, item1, item2)
    then:"We expect a new sale order"
    items.size() == 2
    1 * saleOrder.save()
  }

  void "should send a sale order to confirmation"(){
  given:"A sale order"
    def approvers = [new User(username:"legal@gmail.com").save(validate:false)]
    def company = new Company(rfc:"JIGE930831NZ1",
                                bussinessName:"Apple Computers",
                                webSite:"http://www.apple.com",
                                employeeNumbers:40,
                                grossAnnualBilling:4000).save(validate:false)
    def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
    def saleOrder = service.createSaleOrder(businessEntity, company,new Date().format("dd/MM/yyyy").toString())
  when:"We authoriza a sale order"
    companyService.getAuthorizersByCompany(company) >> approvers
    def result = service.sendOrderToConfirmation(saleOrder)
  then:"We expect new status"
    result.status == SaleOrderStatus.POR_AUTORIZAR
    1 * emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    result instanceof SaleOrder
  }

  void "should authorize a sale order"(){
  given:"A sale order"
    def legalRepresentatives = [new User(username:"legal@gmail.com").save(validate:false)]
    def company = new Company(rfc:"JIGE930831NZ1",
                                bussinessName:"Apple Computers",
                                webSite:"http://www.apple.com",
                                employeeNumbers:40,
                                grossAnnualBilling:4000,
                                legalRepresentatives:legalRepresentatives).save(validate:false)
    def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
    def saleOrder = service.createSaleOrder(businessEntity, company, new Date().format("dd/MM/yyyy").toString())
  when:"We authoriza a sale order"
    def result = service.authorizeSaleOrder(saleOrder)
  then:"We expect new status"
    1 * emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    result.status == SaleOrderStatus.AUTORIZADA
    result instanceof SaleOrder
  }

  void "should add an address to sale order"(){
    given: "A sale order"
      def saleOrder = new SaleOrder()
      saleOrder.save(validate:false)
    and: "an address"
      def address = new Address(street:"street", zipCode:"00000")
      address.save(validate:false)
    when: "we add an address"
      def saleOrderPrepared = service.addTheAddressToSaleOrder(saleOrder, address)
    then: "the sale order must have an address"
      saleOrderPrepared.addresses.size() == 1
      saleOrderPrepared.addresses.first().id == address.id
  }

  void "should execute a sale order"(){
  given:"A sale order"
    SaleOrder saleOrder = new SaleOrder()
    saleOrder.save(validate:false)
  when:"We authoriza a sale order"
    def result = service.executeSaleOrder(saleOrder)
  then:"We expect new status"
    1 * invoiceService.generateFactura(saleOrder) >> "uuid_folio"
    result instanceof SaleOrder
    result.uuid == 'uuid'
    result.folio == 'folio'
  }

  @Ignore
  void "should delete an item of sale order"() {
    given:"A sale order item"
      SaleOrderItem item = Mock(SaleOrderItem)
      item.save(validate:false)
    and:"A sale order"
      def saleOrder = Mock(SaleOrder)
      saleOrder.metaClass.addToItems {
        items.add(item)
      }
      saleOrder.metaClass.removeFromItems {
        items.remove(item)
      }
    when:"We delete item"
      def result = service.deleteItemFromSaleOrder(saleOrder, item)
    then:"We expect item was deleted"
      items.size() == 0
      1 * saleOrder.save()
  }
}
