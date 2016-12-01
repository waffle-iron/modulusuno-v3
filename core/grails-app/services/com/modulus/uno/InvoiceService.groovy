package com.modulus.uno

import grails.util.Environment

class InvoiceService {

  def restService
  def grailsApplication

  private def createInvoiceFromSaleOrder(SaleOrder saleOrder){
    def datosDeFacturacion = new DatosDeFacturacion(folio: "${saleOrder.id}")
    def emisor = new Contribuyente(datosFiscales:new DatosFiscales())
    def receptor = new Contribuyente(datosFiscales:new DatosFiscales())
    def command = new FacturaCommand(datosDeFacturacion:datosDeFacturacion, emisor:emisor, receptor:receptor)
    Company company = saleOrder.company
    Address address = company.addresses.find { addr -> addr.addressType == AddressType.FISCAL }
    command.emisor.datosFiscales.razonSocial = company.bussinessName
    command.emisor.datosFiscales.rfc = company.rfc
    command.emisor.datosFiscales.codigoPostal = address.zipCode
    command.emisor.datosFiscales.pais = address.country
    command.emisor.datosFiscales.ciudad = address.city
    command.emisor.datosFiscales.delegacion = address.town
    command.emisor.datosFiscales.colonia = address.colony
    command.emisor.datosFiscales.calle = address.street
    command.emisor.datosFiscales.noExterior = address.streetNumber
    command.emisor.datosFiscales.noInterior = address.suite ?: "SN"
    command.emisor.datosFiscales.regimen = company.taxRegime.code
    datosDeFacturacion.numeroDeCuentaDePago = company.accounts[0].stpClabe
    command.emitter = company.rfc

    command.receptor.datosFiscales.rfc = saleOrder.rfc
    command.receptor.datosFiscales.razonSocial = saleOrder.clientName
    command.receptor.datosFiscales.pais = saleOrder.addresses[0].country
    command.receptor.datosFiscales.ciudad = saleOrder.addresses[0].city
    command.receptor.datosFiscales.calle = saleOrder.addresses[0].street
    command.receptor.datosFiscales.delegacion = saleOrder.addresses[0].federalEntity
    command.receptor.datosFiscales.codigoPostal = saleOrder.addresses[0].zipCode
    command.receptor.datosFiscales.noExterior = saleOrder.addresses[0].streetNumber ?: "SN"
    command.receptor.datosFiscales.noInterior = saleOrder.addresses[0].suite ?: "SN"
    command.receptor.datosFiscales.colonia = saleOrder.addresses[0].colony

    if(Company.findByRfcAndStatus(command.receptor.datosFiscales.rfc, CompanyStatus.ACCEPTED)){
      command.betweenIntegrated = true
      command.datosDeFacturacion.addendaLabel = "Factura a nombre y cuenta de los socios integrados, como Emisor ${company.bussinessName} con RFC ${company.rfc} y como Receptor ${receptor.datosFiscales.razonSocial} con RFC ${receptor.datosFiscales.rfc}"
    } else {
      command.datosDeFacturacion.addendaLabel = "Factura a nombre y cuenta del Socio Integrado ${company.bussinessName} con RFC ${company.rfc}"
    }

    def conceptos = []
    saleOrder.items.each { item ->
      conceptos.add(new Concepto(cantidad:item.quantity, valorUnitario:item.price, descripcion:item.name, unidad:item.unitType))
    }

    command.conceptos = conceptos

    def impuestos = []
    saleOrder.items.each { item ->
      impuestos.add(new Impuesto(importe:item.quantity * item.price * item.iva / 100, tasa:item.iva, impuesto:'IVA'))
    }

    command.impuestos = impuestos
    command
  }

  String generateFactura(SaleOrder saleOrder){
    def factura = createInvoiceFromSaleOrder(saleOrder)
    def result = restService.sendFacturaCommandWithAuth(factura, grailsApplication.config.modulus.facturaCreate)
    result.str
  }

  void cancelBill(SaleOrder saleOrder) {
    CancelBillCommand cancelCommand = new CancelBillCommand(uuid:"${saleOrder.uuid}", rfc:"${saleOrder.company.rfc}")
    restService.sendFacturaCommandWithAuth(cancelCommand, grailsApplication.config.modulus.cancelFactura)
  }

}
