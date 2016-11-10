package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PurchaseOrderItemController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond PurchaseOrderItem.list(params), model:[purchaseOrderItemCount: PurchaseOrderItem.count()]
  }

  def show(PurchaseOrderItem purchaseOrderItem) {
    respond purchaseOrderItem
  }

  def create() {
    respond new PurchaseOrderItem(params)
  }

  @Transactional
  def save(PurchaseOrderItemCommand command) {
    if (!command) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    def purchaseOrder = PurchaseOrder.findById(params.purchaseOrder.id)
    def purchaseOrderItem = command.createPurchaseOrderItem()
    purchaseOrderItem.purchaseOrder = purchaseOrder

    if (purchaseOrderItem.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond purchaseOrderItem.errors, view:'create'
      return
    }

    purchaseOrderItem.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = purchaseOrder.isMoneyBackOrder ? message(code: 'moneyBackOrderItem.created', args:[:]) : message(code: 'purchaseOrderItem.created', args: [:])
        redirect controller:'purchaseOrder', action:'show', id:purchaseOrderItem.purchaseOrder.id, params:params
      }
      '*' { respond purchaseOrderItem, [status: CREATED] }
    }
  }

  def edit(PurchaseOrderItem purchaseOrderItem) {
    respond purchaseOrderItem
  }

  @Transactional
  def update(PurchaseOrderItemCommand command) {
    if (!command) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    def purchaseOrderItem = PurchaseOrderItem.findById(params.id)
    def purchaseOrder = purchaseOrderItem.purchaseOrder
    purchaseOrderItem.properties = command.createPurchaseOrderItem.properties
    purchaseOrderItem.purchaseOrder = purchaseOrder
    if (purchaseOrderItem.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond purchaseOrderItem.errors, view:'edit'
      return
    }

    purchaseOrderItem.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), purchaseOrderItem.id])
        redirect purchaseOrderItem
      }
      '*'{ respond purchaseOrderItem, [status: OK] }
    }
  }

  @Transactional
  def delete(PurchaseOrderItem purchaseOrderItem) {

    if (purchaseOrderItem == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    purchaseOrderItem.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), purchaseOrderItem.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseOrderItem.label', default: 'PurchaseOrderItem'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
