package com.modulus.uno
import grails.validation.Validateable

class StpDepositCommand implements Validateable {
  String clave
  String fechaOperacion
  String institucionOperante
  String institucionBeneficiaria
  String claveRastreo
  String monto
  String nombreOrdenante
  String nombreBeneficiario
  String tipoCuentaBeneficiario
  String cuentaBeneficiario
  String rfcCurpCuentaBeneficiario
  String conceptoPago
  String referenciaNumerica
  String empresa
}
