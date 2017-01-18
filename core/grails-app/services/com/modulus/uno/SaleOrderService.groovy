package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class SaleOrderService {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
  def emailSenderService
  def invoiceService
  def grailsApplication
  def companyService
  def springSecurityService

  // TODO: Code Review
  def createSaleOrderWithAddress(Long companyId, Long clientId, Long addressId, def fechaCobro, String externalId, def note) {
    if(!companyId && !clientId && !addressId){
      throw new BusinessException("No se puede crear la orden de venta...")
    }
    Company company = Company.get(companyId)
    BusinessEntity businessEntity = BusinessEntity.get(clientId)
    SaleOrder saleOrder = createSaleOrder(businessEntity, company, fechaCobro, externalId, note)
    Address address = Address.get(addressId)
    addTheAddressToSaleOrder(saleOrder, address)
    saleOrder
  }

  def createSaleOrder(BusinessEntity businessEntity, Company company, def fechaCobro, String externalId, String note) {
    def saleOrder = new SaleOrder(rfc:businessEntity.rfc, clientName: businessEntity.toString(), company:company, externalId:externalId, note:note)
    saleOrder.status = SaleOrderStatus.CREADA
    saleOrder.fechaCobro = Date.parse("dd/MM/yyyy", fechaCobro)
    saleOrder.save()
    saleOrder
  }

  def addItemToSaleOrder(SaleOrder saleOrder, SaleOrderItem... items){
    items.each {
      saleOrder.addToItems(it)
    }
    saleOrder.save()
  }

  def sendOrderToConfirmation(SaleOrder saleOrder){
    saleOrder.status = SaleOrderStatus.POR_AUTORIZAR
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    saleOrder
  }

  Boolean isFullAuthorized (SaleOrder saleOrder) {
    def alreadyAuthorizations = saleOrder.authorizations ? saleOrder.authorizations.size() : 0
    alreadyAuthorizations >= saleOrder.company.numberOfAuthorizations
  }

  def addAuthorizationToSaleOrder(SaleOrder saleOrder, User user) {
    Authorization authorization = new Authorization(user:user).save()
    saleOrder.addToAuthorizations(authorization)
    saleOrder.save()
  }

  def authorizeSaleOrder(SaleOrder saleOrder){
    saleOrder.status = SaleOrderStatus.AUTORIZADA
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    saleOrder
  }

  def executeSaleOrder(SaleOrder saleOrder){
    String uuidFolio = invoiceService.generateFactura(saleOrder)
    updateSaleOrderFromGeneratedBill(uuidFolio, saleOrder)
  }

  private updateSaleOrderFromGeneratedBill(String uuidFolio, SaleOrder saleOrder) {
    def tokens = uuidFolio.tokenize("_")
    saleOrder.uuid = tokens[0]
    saleOrder.folio = tokens[1]
    saleOrder.status = SaleOrderStatus.EJECUTADA
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    saleOrder
  }

  def executeCancelBill(SaleOrder saleOrder) {
    invoiceService.cancelBill(saleOrder)
    saleOrder.status = SaleOrderStatus.CANCELACION_EJECUTADA
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
  }

  String getFactura(SaleOrder saleOrder, String format){
    "${grailsApplication.config.modulus.facturacionUrl}${grailsApplication.config.modulus.showFactura}/${saleOrder.uuid}_${saleOrder.folio}/${format}"
  }

  def addTheAddressToSaleOrder(SaleOrder saleOrder, Address address){
    saleOrder.addToAddresses(address)
    saleOrder.save()
    saleOrder
  }

  def generatePreviewInvoiceForSaleOrderWithTemplate(SaleOrder saleOrder) {
    invoiceService.generatePreviewFactura(saleOrder)
  }

  def getTotalSaleOrderAuthorizedOfCompany(Company company){
    SaleOrder.findAllByCompanyAndStatus(company, SaleOrderStatus.AUTORIZADA).total.sum()
  }

  def getSaleOrderStatus(String status){
    def saleOrderStatuses = []
    saleOrderStatuses = Arrays.asList(SaleOrderStatus.values())
    if (status){
      def listStatus = status.tokenize(",")
      saleOrderStatuses = listStatus.collect { it as SaleOrderStatus }
    }

    saleOrderStatuses
  }

  def getSaleOrdersToList(Long company, params){
    def statusOrders = getSaleOrderStatus(params.status)
    def saleOrders = [:]
    if(company){
      saleOrders.list = SaleOrder.findAllByCompanyAndStatusInList(Company.get(company), statusOrders, params)
      saleOrders.items = SaleOrder.countByCompanyAndStatusInList(Company.get(company), statusOrders)
    } else{
      saleOrders.list = SaleOrder.findAllByStatusInList(statusOrders, params)
      saleOrders.items = SaleOrder.countByStatusInList(statusOrders)
    }
    saleOrders
  }

  def deleteItemFromSaleOrder(SaleOrder saleOrder, SaleOrderItem item) {
    SaleOrderItem.executeUpdate("delete SaleOrderItem item where item.id = :id", [id: item.id])
  }

  def createOrUpdateSaleOrder(def params) {
    SaleOrder saleOrder = SaleOrder.findByExternalId(params.externalId)
    if (saleOrder) {
      saleOrder.fechaCobro = Date.parse("dd/MM/yyyy", params.fechaCobro)
      saleOrder.save()
    } else {
      saleOrder = createSaleOrderWithAddress(params.long("companyId"),params.long("clientId"),params.long("addressId"), params.fechaCobro, params.externalId, params.note)
    }
    saleOrder
  }

  def updateDateChargeForOrder(Long id, Date chargeDate) {
    SaleOrder saleOrder = SaleOrder.get(id)
    if (!saleOrder.originalDate)
      saleOrder.originalDate = saleOrder.fechaCobro
    saleOrder.fechaCobro = chargeDate
    saleOrder.save()
    saleOrder
  }

  List<SaleOrder> obtainListPastDuePortfolio(Long idCompany, Integer days) {
    Company company = Company.get(idCompany)
    Date begin = new Date()-days
    if (days==120) {
      SaleOrder.findAllByCompanyAndOriginalDateGreaterThanEquals(company, begin)
    } else {
      Date end = new Date()-(days+30)
      SaleOrder.findAllByCompanyAndOriginalDateBetween(company, begin, end)
    }
  }
}
