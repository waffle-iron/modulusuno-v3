package com.modulus.uno

import grails.validation.Validateable
import java.text.*

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

  StpDeposit createStpDeposit() {
    new StpDeposit(
      operationNumber:new this.clave.toLong()
      operationDate: new Date(this.fechaOperacion.toLong()*1000)
      payerKey: this.institucionOperante
      beneficiaryKey: this.institucionBeneficiaria
      tracingKey: this.claveRastreo
      amount: getValueInBigDecimal(this.monto)
      payerName: this.nombreOrdenante
      beneficiaryName: this.nombreBeneficiario
      typeAccountBeneficiary: this.tipoCuentaBeneficiario.toLong()
      accountBeneficiary: this.cuentaBeneficiario
      rfcCurpBeneficiary: this.rfcCurpCuentaBeneficiario
      paymentConcept: this.conceptoPago
      numericalReference: this.referenciaNumerica.toLong()
      companyNameStp: this.empresa
    )
  }

  private def getValueInBigDecimal(String value) {
    Locale.setDefault(new Locale("es","MX"));
    DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
    df.setParseBigDecimal(true);
    BigDecimal bd = (BigDecimal) df.parse(value);
    bd
  }

}
