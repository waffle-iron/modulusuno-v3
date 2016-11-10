package com.modulus.uno

class BusinessEntityTagLib {
  static namespace = "my"
  static defaultEncodeAs = [taglib:'html']

  def clientService
  def providerService
  def employeeService

  def whatIsThisBusinessEntity = { attrs ->
    String type = ""
    type = clientService.isClient(attrs.be) ? "Cliente" : ""
    type += providerService.isProvider(attrs.be) ? (type ? " - Proveedor" : "Proveedor") : ""
    type += employeeService.isEmployee(attrs.be) ? (type ? " - Emp/Colaborador" : "Emp/Colaborador") : ""
    out <<  type
  }

}
