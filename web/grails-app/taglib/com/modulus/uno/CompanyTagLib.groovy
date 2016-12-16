package com.modulus.uno

class CompanyTagLib {

  def springSecurityService

  static namespace = "modulusuno"
  static defaultEncodeAs = [taglib:'text']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def statusForCompany = { attrs, body ->
    def labelStatus = [
      (CompanyStatus.CREATED) : "label-primary",
      (CompanyStatus.VALIDATE): "label-warning",
      (CompanyStatus.ACCEPTED): "label-success",
      (CompanyStatus.REJECTED): "label-danger" ]
    out << """
      <span class="label ${labelStatus[attrs.status] ?: 'label-default'}">
        ${g.message(code:attrs.status.code)}
      </span>
    """
  }

  def userLoggin = { attrs, body ->
    out << "${springSecurityService.currentUser.username}"
  }

  def amountAccountToday = { attrs,body ->
    def bankAccount = BankAccount.findById(attrs.id)
    def movimientosBancarios = MovimientosBancarios.findAllByCuenta(bankAccount)
    def movDebito = movimientosBancarios.findAll { mov -> mov.type == MovimientoBancarioType.DEBITO }
    def movCredito = movimientosBancarios.findAll { mov -> mov.type == MovimientoBancarioType.CREDITO }
    def debito = movDebito.sum { it.amount } ?: 0
    def credito = movCredito.sum { it.amount } ?: 0
    def amount = credito - debito
    out <<  g.formatNumber(number:amount, type:"currency", maxFractionDigits:"2", locale:"es_MX")

  }

}
