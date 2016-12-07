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
    def debito = movimientosBancarios.find { mov -> mov.type == MovimientoBancarioType.DEBITO }
    def credito = movimientosBancarios.find { mov -> mov.type == MovimientoBancarioType.CREDITO }
    def amount = credito*.amount?.sum()?:0 - debito*.amount?.sum()?:0
    out << amount
  }

}
