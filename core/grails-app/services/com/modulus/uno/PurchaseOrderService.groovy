package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class PurchaseOrderService {

  def documentService
  def emailSenderService
  def modulusUnoService
  def restService

  def addAuthorizationToPurchaseOrder(PurchaseOrder order, User user) {
    def authorization = new Authorization(user:user)
    order.addToAuthorizations(authorization)
    order.save()
  }

  def addInvoiceToPurchaseOrder(def invoice, Long purchaseOrderId, String type){
    PurchaseOrder order = PurchaseOrder.get(purchaseOrderId)
    documentService.uploadDocumentForOrder(invoice,type,order)
  }

  def authorizePurchaseOrder(PurchaseOrder purchaseOrder){
    purchaseOrder.status = PurchaseOrderStatus.AUTORIZADA
    purchaseOrder.save()
    emailSenderService.notifyPurchaseOrderChangeStatus(purchaseOrder)
    purchaseOrder
  }

  def callExternalServiceForInvoice(def invoice) {
    File tmp = File.createTempFile("${new File(".").getCanonicalPath()}/${invoice.originalFilename}","")
    invoice.transferTo(tmp)
    def responsePOST = restService.getInvoiceData(tmp.bytes)
    tmp.deleteOnExit()
    responsePOST
  }

  def createPurchaseOrder(Long companyId, Map params) {
    Company company = Company.get(companyId)
    BusinessEntity businessEntity = BusinessEntity.get(params.providerId)
    BankAccount bankAccount = BankAccount.get(params.bankAccountId)
    def purchaseOrder = new PurchaseOrder(rfc:businessEntity.rfc, providerName: businessEntity.toString(), company:company, isMoneyBackOrder:params.isMoneyBackOrder, fechaPago:Date.parse("dd/MM/yyyy", params.fechaPago), externalId:params.externalId,note:params.note)
    if (params.orderType == "0")
      purchaseOrder.isAnticipated = true

    purchaseOrder.bankAccount = bankAccount
    purchaseOrder.status = PurchaseOrderStatus.CREADA
    purchaseOrder.save()
    purchaseOrder
  }

  private Boolean statusIsValidToAddDocuments(PurchaseOrder purchaseOrder) {
    Boolean isValid = purchaseOrder.status != PurchaseOrderStatus.CANCELADA &&
    purchaseOrder.status != PurchaseOrderStatus.RECHAZADA

    if (purchaseOrder.isAnticipated)
      isValid = isValid && purchaseOrder.status != PurchaseOrderStatus.CREADA

    isValid
  }

  private Boolean missingDocumentsByTypePurchaseOrder(PurchaseOrder purchaseOrder) {
    Integer maxDocuments = purchaseOrder.isMoneyBackOrder ? 3 : 2
    Integer currentDocuments = purchaseOrder.documents ? purchaseOrder.documents.size() : 0
    currentDocuments < maxDocuments
  }

  Boolean enableAddDocuments(PurchaseOrder purchaseOrder) {
    statusIsValidToAddDocuments(purchaseOrder) && missingDocumentsByTypePurchaseOrder(purchaseOrder) && purchaseOrder.items
  }

  def createPurchaseOrderByInvoice(def dataOfInvoice,def companyId) {
    def businessEntity = BusinessEntity.findByRfc(dataOfInvoice.emisor.rfc)
    def params= ["providerId":businessEntity.id,"bankAccountId":businessEntity.banksAccounts.first().id,"isMoneyBackOrder":false]
    createPurchaseOrder(companyId,params)
  }

  def createPurchaseOrderItemsByInvoice(def dataOfInvoice,PurchaseOrder purchaseOrder) {
    def itemList = dataOfInvoice.conceptos.first()
    itemList.each { item ->
      def command = new PurchaseOrderItemCommand()
      command.name = item.descripcion
      command.quantity = item.cantidad
      command.price = item.importe
      command.unitType = getUnitTypeByUnitOfConcept(item.unidad)
      def purchaseOrderItem = command.createPurchaseOrderItem()
      purchaseOrder.addToItems(purchaseOrderItem)
    }
    purchaseOrder.save()
  }

  def getPurchaseOrderStatus(String status){
    def listPurchaseOrderStatus = []
    listPurchaseOrderStatus = Arrays.asList(PurchaseOrderStatus.values())
    if (status){
      def listStatus = status.tokenize(",")
      listPurchaseOrderStatus = listStatus.collect { it as PurchaseOrderStatus }
    }

    listPurchaseOrderStatus
  }

  def getPurchaseOrdersToList(Long company, params){
    def statusOrders = getPurchaseOrderStatus(params.status)
    def purchaseOrders = [:]
    if (company){
      purchaseOrders.list = PurchaseOrder.findAllNotIsMoneyBackOrderByCompanyAndStatusInList(Company.get(company), statusOrders, params)
      purchaseOrders.items = PurchaseOrder.countByCompanyAndStatusInListAndIsMoneyBackOrder(Company.get(company), statusOrders, false)
    } else {
      purchaseOrders.list = PurchaseOrder.findAllNotIsMoneyBackOrderByStatusInList(statusOrders, params)
      purchaseOrders.items = PurchaseOrder.countByStatusInListAndIsMoneyBackOrder(statusOrders, false)
    }
    purchaseOrders
  }

  def getMoneyBackOrdersToList(Long company, params){
    def statusOrders = getPurchaseOrderStatus(params.status)
    def purchaseOrders = [:]
    if (company){
      purchaseOrders.list = PurchaseOrder.findAllIsMoneyBackOrderByCompanyAndStatusInList(Company.get(company), statusOrders, params)
      purchaseOrders.items = PurchaseOrder.countByCompanyAndStatusInListAndIsMoneyBackOrder(Company.get(company), statusOrders, true)
    } else {
      purchaseOrders.list = PurchaseOrder.findAllIsMoneyBackOrderByStatusInList(statusOrders, params)
      purchaseOrders.items = PurchaseOrder.countByStatusInListAndIsMoneyBackOrder(statusOrders, true)
    }
    purchaseOrders
  }


  def getTotalPurchaseOrderAuthorizedOfCompany(Company company){
    PurchaseOrder.findAllByCompanyAndStatus(company, PurchaseOrderStatus.AUTORIZADA).total.sum()
  }

  private def getUnitTypeByUnitOfConcept(String unit) {
    switch (unit){
      case {it =~ /[pzPZ]/}:
        return UnitType.UNIDADES
      break
      case {it =~/[KGkg]/}:
        return UnitType.KILOGRAMOS
      break
      case {it =~/Mm/}:
        return UnitType.METROS
      break
      case {it =~/Ll/}:
        return UnitType.LITROS
      break
      case {it =~/HRShrs/}:
        return UnitType.HORAS
      break
      default:
        return UnitType.UNIDADES
      break
    }
  }

  def isFullAuthorized(PurchaseOrder order){
    (order.authorizations?.size() ?: 0) >= order.company.numberOfAuthorizations
  }

  def payPurchaseOrder(PurchaseOrder order, PaymentToPurchase payment){
    modulusUnoService.payPurchaseOrder(order, payment)
    if (amountPaymentIsTotalForPurchaseOrder(order, payment)) {
      order.status = PurchaseOrderStatus.PAGADA
      order.save()
    }
    emailSenderService.notifyPurchaseOrderChangeStatus(order)
    order
  }

  def requestAuthorizationForTheOrder(PurchaseOrder purchaseOrder){
    purchaseOrder.status = PurchaseOrderStatus.POR_AUTORIZAR
    purchaseOrder.save()
    emailSenderService.notifyPurchaseOrderChangeStatus(purchaseOrder)
    purchaseOrder
 }

  def validateDocument(PurchaseOrder order, def document){
    String ext = document.filename.tokenize('.').last().toLowerCase()
    boolean valid = true
    if ((!order.isMoneyBackOrder && !ext.equals("pdf") && !ext.equals("xml"))
        || (order.isMoneyBackOrder && !ext.equals("pdf") && !ext.equals("xml") && !ext.equals("png") && !ext.equals("jpg")))
      valid = false
    else if (order.documents){
      order.documents.each(){
        if ((it.mimeType.equals(ext) && !order.isMoneyBackOrder) ||
            (order.isMoneyBackOrder && ((it.mimeType.equals("png") && ext.equals("jpg"))
            || (it.mimeType.equals("jpg") && ext.equals("png"))
            || (!ext.equals("pdf") && it.mimeType.equals(ext)))
            )
        )
          valid = false
      }
    }
    valid
  }

  def updateDatePaymentForOrder(Long id, Date paymentDate) {
    PurchaseOrder purchaseOrder = PurchaseOrder.get(id)
    if (!purchaseOrder.originalDate)
      purchaseOrder.originalDate = purchaseOrder.fechaPago
    purchaseOrder.fechaPago = paymentDate
    purchaseOrder.save()
    purchaseOrder
  }

  def addingPaymentToPurchaseOrder(PaymentToPurchase payment, PurchaseOrder purchaseOrder) {
    purchaseOrder.addToPayments(payment)
    purchaseOrder.save()
    purchaseOrder
  }

  boolean amountPaymentIsTotalForPurchaseOrder(PurchaseOrder purchaseOrder, PaymentToPurchase payment) {
    def amountPurchase = purchaseOrder.total
    def amountPayments = (purchaseOrder.totalPayments + payment.amount)
    amountPurchase <= amountPayments
  }

  Boolean amountExceedsTotal(def amount, PurchaseOrder order) {
    def originalAmount = order.total
    def totalAmountPayments = order.totalPayments
    if (amount <= originalAmount)
      if (amount <= (originalAmount - totalAmountPayments))
        return false
    return true
  }

  def deleteItemFromPurchaseOrder(PurchaseOrderItem item) {
    PurchaseOrderItem.executeUpdate("delete PurchaseOrderItem item where item.id = :id", [id: item.id])
  }

}
