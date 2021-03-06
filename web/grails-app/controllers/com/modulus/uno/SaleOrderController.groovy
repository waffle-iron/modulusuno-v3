package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
import wslite.rest.*
import grails.transaction.Transactional

@Transactional
class SaleOrderController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def clientService
  def businessEntityService
  SaleOrderService saleOrderService
  def springSecurityService
  def companyService
  def documentService
  def businessEntity
  def s3AssetService
  def emailSenderService

  def authorizeSaleOrder(SaleOrder saleOrder){
    saleOrder = saleOrderService.addAuthorizationToSaleOrder(saleOrder, springSecurityService.currentUser)
    if (saleOrderService.isFullAuthorized(saleOrder)) {
      saleOrderService.authorizeSaleOrder(saleOrder)
    }

    redirect action:'list', params:[status:'POR_AUTORIZAR']
  }

  def authorizeCancelBill(SaleOrder saleOrder) {
    saleOrder.status = SaleOrderStatus.CANCELACION_AUTORIZADA
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    redirect action:'list', params:[status:'CANCELACION_POR_AUTORIZAR']
  }


  def create() {
    def company = Company.get(session.company ? session.company.toLong() : params.companyId)
    respond new SaleOrder(params),model: [company:company]
  }

  def edit(SaleOrder saleOrder) {
    respond saleOrder
  }

  def cancelSaleOrder(SaleOrder saleOrder){
    flash.message = message(code: 'saleOrder.cancel', args: [:])
    saleOrder.status = SaleOrderStatus.CANCELADA
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    redirect action:'list', params:[companyId:saleOrder.company.id, status:"${SaleOrderStatus.POR_AUTORIZAR}"]
  }

  def chooseClientForSale(){
    def company = Company.get(session.company.toLong())
    def clients = businessEntityService.findBusinessEntityByKeyword(params.q, "CLIENT", company)
    def client = BusinessEntity.get(params.id)
    render view:'create',model:([company:company, clients:clients, client:client] + params)
  }

  def deleteOrder(SaleOrder saleOrder) {
    saleOrderService.deleteSaleOrder(saleOrder)
    redirect action:'list'
  }

  def executeSaleOrder(SaleOrder saleOrder){
    log.info "Execute saleOrder ${saleOrder.id} with pdf template ${saleOrder.pdfTemplate}"
    String messageSuccess = message(code:"saleOrder.already.executed")
    if (saleOrderIsInStatus(saleOrder, SaleOrderStatus.AUTORIZADA)) {
      saleOrderService.executeSaleOrder(saleOrder)
      messageSuccess = message(code:"saleOrder.executed.message")
    }
    redirect action:'list', params:[messageSuccess:messageSuccess]
  }

  private Boolean saleOrderIsInStatus(SaleOrder saleOrder, def statusExpected) {
    saleOrder.status == statusExpected
  }

  def executeCancelBill(SaleOrder saleOrder) {
    saleOrderService.executeCancelBill(saleOrder)
    redirect action:'list', params:[status:"${SaleOrderStatus.CANCELACION_AUTORIZADA}"]
  }

  def getProduct(){
    def company = Company.get(session.company)
    def product = Product.findByNameAndCompany(params.nombre, company)
    // TODO: Aqui podría ir un marshaller
    Map model = [:]
    model.sku = product.sku
    model.price = product.price
    model.ieps = product.ieps
    model.iva = product.iva
    model.unit = product.unitType.name()
    render model as JSON
  }

  def getProductBySku(){
    def company = Company.get(session.company)
    def product = Product.findBySkuAndCompany(params.sku, company)
    // TODO: Aqui podría ir un marshaller
    Map model = [:]
    model.productName = product.name
    model.price = product.price
    model.ieps = product.ieps
    model.iva = product.iva
    model.unit = product.unitType.name()
    render model as JSON
  }

  def index() {
    def roles = springSecurityService.getPrincipal().getAuthorities()
    def company = Company.get(session.company ?: params.companyId)
    respond SaleOrder.findAllByCompany(company), model:[company:company]
  }

  def list() {
    params.max = params.max ?: 10
    def saleOrders = [:]
    saleOrders = saleOrderService.getSaleOrdersToList(session.company?session.company.toLong():session.company, params)

    [saleOrders: saleOrders.list, saleOrderCount: saleOrders.items, messageSuccess:params.messageSuccess]
  }

  def listProducts(){
    def company = Company.get(session.company)
    def products = []
    if (params.pname)
      products = Product.findAllByNameIlikeAndCompany("%${params.pname}%", company)
    else if (params.psku)
      products = Product.findAllBySkuIlikeAndCompany("%${params.psku}%", company)
      render products as JSON
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'saleOrder.label', default: 'SaleOrder'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }

  def previewInvoicePdf(SaleOrder saleOrder) {
    log.info "Showing preview pdf for SaleOrder: ${saleOrder.id} with template: ${saleOrder.pdfTemplate}"
    def file = saleOrderService.generatePreviewInvoiceForSaleOrderWithTemplate(saleOrder)
    response.setContentType("application/pdf")
    response.outputStream << file
  }

  def save() {
    def saleOrder = saleOrderService.createSaleOrderWithAddress(params)
    if (saleOrder.id) {
      redirect action:'show', id:saleOrder.id
    } else {
      flash.message = "No se pudo crear la orden de venta"
      redirect action:'create'
    }
  }

  def searchClientForSale(){
    def company = Company.get(session.company ? session.company.toLong() : params.companyId)
    def clients = businessEntityService.findBusinessEntityByKeyword(params.q, "CLIENT", company)
    //TODO : Filtrar a los proveedores...
    if(clients.isEmpty()){
      flash.message = "No se encontró cliente."
    }
    render view:'create',model:([company:company, clients:clients] + params)
  }

  def sendOrderToConfirmation(SaleOrder saleOrder){
    saleOrderService.sendOrderToConfirmation(saleOrder)
    flash.message = message(code: 'saleOrder.validation', args: [:])
    redirect action:'list'
  }

  def show(SaleOrder saleOrder) {
    respond saleOrder, model:[saleOrderItem: new SaleOrderItem(), user:springSecurityService.currentUser, isEnabledToStamp:companyService.isCompanyEnabledToStamp(saleOrder.company)]
  }

  def showFactura(SaleOrder saleOrder){
    saleOrderService.getFactura(saleOrder, 'xml')
    redirect action:'index', params:[companyId:saleOrder.company.id]
  }

  def showSaleOrdersByAuthorize() {
    def user = springSecurityService.currentUser
    def companies = companyService.findCompaniesForThisUser(user)
    [companies:companies]
  }

  def rejectSaleOrder(SaleOrder saleOrder){
    saleOrder.status = SaleOrderStatus.RECHAZADA
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    flash.message = message(code: 'saleOrder.execute', args: [:])
    redirect action:'list'
  }

  def update(SaleOrder saleOrder) {
    if (saleOrder == null) {
      notFound()
      return
    }

    if (saleOrder.hasErrors()) {
      respond saleOrder.errors, view:'edit'
      return
    }

    saleOrder.save()

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'saleOrder.label', default: 'SaleOrder'), saleOrder.id])
        redirect saleOrder
      }
      '*'{ respond saleOrder, [status: OK] }
    }
  }

  def requestCancelBill(SaleOrder saleOrder) {
    saleOrder.status = SaleOrderStatus.CANCELACION_POR_AUTORIZAR
    saleOrder.save()
    emailSenderService.notifySaleOrderChangeStatus(saleOrder)
    redirect action:'list'
  }

  def deleteItem(SaleOrderItem item) {
    Long idSaleOrder = item.saleOrder.id
    saleOrderService.deleteItemFromSaleOrder(item.saleOrder, item)
    redirect action:'show', id:idSaleOrder
  }

  def conciliationSaleOrderPerClients() {
    def company = Company.get(session.company)
    def clients = businessEntityService.findBusinessEntityByKeyword(params.q, "CLIENT", company)
    [clients: clients,params:params]
  }

  def searchSaleOrderExcecuteByClient() {
    Company company = Company.get(session.company)
    List saleOrders = SaleOrder.findAllByRfcAndStatus(params.rfc, SaleOrderStatus.EJECUTADA)
    List payments = Payment.findAllByRfcAndStatus(params.rfc, PaymentStatus.PENDING)
    render view:"/payment/reconcile", model:[payments: payments, saleOrders:saleOrders, company: company]
  }

  def applyDiscount(SaleOrder saleOrder) {
    redirect action:"show", id:saleOrder.id
  }
}
