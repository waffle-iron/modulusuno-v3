package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

@Api
class STPController {

  static allowedMethods = [depositSTP: "POST", stpDepositNotification:"POST"]

  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = "clave", value = "Folio de la operación en Enlace Financiero", required = true, dataType = "long", paramType = "query"),
    @ApiImplicitParam(name = "fechaOperacion", value = "Fecha de Operación", required = true, dataType = "long", paramType = "query"),
    @ApiImplicitParam(name = "institucionOrdenante", value = "Clave de la institución que genera el pago", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "institucionBeneficiaria", value = "Clave de la institución a la que va dirigida el pago", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "claveRastreo", value = "Clave de rastreo asociada a la orden", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "monto", value = "Monto de la orden de pago", required = true, dataType = "long", paramType = "query"),
    @ApiImplicitParam(name = "nombreOrdenante", value = "El nombre del ordenante asociado a la orden", required = false, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "nombreBeneficiario", value = "El nombre del beneficiario asociado a la orden", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "tipoCuentaBeneficiario", value = "Tipo de la cuenta del beneficiario", required = true, dataType = "long", paramType = "query"),
    @ApiImplicitParam(name = "cuentaBeneficiario", value = "Cuenta del beneficiario", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "rfcCurpCuentaBeneficiario", value = "RFC o CURP del beneficiario", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "conceptoPago", value = "Concepto del pago", required = true, dataType = "string", paramType = "query"),
    @ApiImplicitParam(name = "referenciaNumerica", value = "Referencia numérica asociada al pago", required = true, dataType = "long", paramType = "query"),
    @ApiImplicitParam(name = "empresa", value = "Nombre de la empresa beneficiaria que está configurada en STP", required = true, dataType = "string", paramType = "query")
      ])
  def depositSTP(StpDepositCommand command) {
    respond new StpDeposit(), status: 201, formats:['json']
  }

  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = 'file', value = '', dataType = 'file',paramType = 'form')
  ])
  def stpDepositNotificationFile() {
    def notificationXml = params.file
    println "Xml: ${notificationXml}"
    respond new StpDeposit(), status: 201, formats: ['json']
  }

  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = 'notification', value = '', dataType = 'string',paramType = 'query')
  ])
  def stpDepositNotification() {
    println "String Xml: ${params.notification}"
    respond new StpDeposit(), status: 201, formats: ['json']
  }

}
