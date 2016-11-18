package com.modulus.uno

class SaleOrderTagLib {

  static namespace = "integradora"
  static defaultEncodeAs = [taglib:'html']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def formatPrice = { attrs, body ->
    out << g.formatNumber(number:attrs.number, type:"currency", maxFractionDigits:"2", locale:"es_MX")
  }

  def invoiceUrl = { attrs, body ->
    out << "${grailsApplication.config.modulus.facturacionUrl}${createUrlToShowFile(attrs)}"
  }

  private def createUrlToShowFile(def attrs) {
    def file = "${attrs.saleOrder.uuid}_${attrs.saleOrder.folio}.${attrs.format}"
    def rfc = "${attrs.saleOrder.company.rfc}"
    def url = grailsApplication.config.modulus.showFactura
    url.replace('#rfc',rfc).replace('#file',file)
  }

}
