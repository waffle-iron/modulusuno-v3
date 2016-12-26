package com.modulus.uno

import grails.transaction.Transactional
import java.math.RoundingMode

@Transactional
class StpDepositService {

  def notificationDepositFromStp(String xmlNotif) {
    def notification = new XmlSlurper().parseText(xmlNotif)
    StpDepositCommand command = new StpDepositCommand(
      clave:notification.Clave,
      fechaOperacion:notification.FechaOperacion,
      institucionOperante:notification.InstitucionOperante.@clave,
      institucionBeneficiaria:notification.InstitucionBeneficiaria.@clave,
      claveRastreo:notification.ClaveRastreo,
      monto:notification.Monto,
      nombreOrdenante:notification.NombreOrdenante,
      tipoCuentaBeneficiario:notification.TipoCuentaBeneficiario,
      cuentaBeneficiario:notification.CuentaBeneficiario,
      rfcCurpCuentaBeneficiario:notification.RfcCurpCuentaBeneficiario,
      conceptoPago:notification.ConceptoPago,
      referenciaNumerica:notification.ReferenciaNumerica,
      empresa:notification.Empresa
    )
    if (!command.validate()) {
      throw new BusinessException("Invalid parameters")
    }

    StpDeposit stpDeposit = command.createStpDeposit()
    stpDeposit.save()
    //actualizar saldo de cuenta bancaria stp
    //registrar cashin en M1
  }

}

