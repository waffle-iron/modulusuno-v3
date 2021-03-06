package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

@Api
class SaleOrderController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE",saveSaleOrderItem: "POST"]

  def clientService
  def businessEntityService
  def saleOrderService
  def companyService

  @SwaggyList
  def index() {
    respond SaleOrder.list(),status: 200, formats: ['json']
  }

  @SwaggyShow
  def show() {
    def saleOrder = SaleOrder.findById(params.id)
    if (saleOrder)
      respond saleOrder, status: 200, formats: ['json']
    else
      response.sendError(404,"SaleOrder not Exist or need send a id" )
  }

  @SwaggySave(extraParams = [
      @ApiImplicitParam(name = 'companyId', value = '', required = true, dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'clientId', value = '', required = true, dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'addressId', value = '', required = true, dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'fechaCobro', value = 'dd/MM/yyyy', required = true, dataType = 'date',paramType = 'form'),
      @ApiImplicitParam(name = 'note', value = '', required = false, dataType = 'string',paramType = 'form'),
      @ApiImplicitParam(name = 'externalId', value = '', required = true, dataType = 'string',paramType = 'form')
      ])
  def save() {
    def saleOrder = saleOrderService.createOrUpdateSaleOrder(params)
    saleOrderService.sendOrderToConfirmation(saleOrder)
    respond saleOrder, status:201, formats: ['json']
  }


  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = 'sku', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'name', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'quantity', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'price', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'ieps', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'iva', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'unitType', value = 'UNIDADES,KILOGRAMOS,METROS,LITROS,HORAS,SERVICIO,PAQUETES,CAJA, PIEZA,TONELADAS,TAMBOS', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'saleOrderId', value = '', dataType = 'number',paramType = 'form')
      ])
  def saveSaleOrderItem(SaleOrderItemCommand command) {
    def saleOrder = SaleOrder.findById(params.saleOrderId)
    def saleOrderItem = command.createSaleOrderItem()
    saleOrderItem.saleOrder = saleOrder

    saleOrderItem.save()

    if(saleOrderItem)
      respond saleOrderItem, status:201, formats: ['json']
    else
      response.senErrod(404, "Missing fields for item")

  }

  @SwaggyShow
  def getDocumentInvoiceById() {
    SaleOrder saleOrder = SaleOrder.findById(params.id)
    if (saleOrder.documents)
      respond saleOrder.documents*.localUrl, status:200, formats: ['json']
    else
      response.sendError(400, "SaleOrder not contain documents, is possible it was not executeded")
  }

}
