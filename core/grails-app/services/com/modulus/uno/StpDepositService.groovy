package com.modulus.uno

import grails.transaction.Transactional
import java.math.RoundingMode

@Transactional
class StpDepositService {

  def notificationDepositFromStp(String xmlNotif) {
    def notification = new XmlSlurper().parseText(xmlNotif)
    log.info "Notification: ${notification}"

    StpDepositCommand command = new StpDepositCommand(
      clave:notification.Clave,
      fechaOperacion:notification.FechaOperacion,
      institucionOrdenante:notification.InstitucionOrdenante.@clave,
      institucionBeneficiaria:notification.InstitucionBeneficiaria.@clave,
      claveRastreo:notification.ClaveRastreo,
      monto:notification.Monto,
      nombreOrdenante:notification.NombreOrdenante ?: "",
      nombreBeneficiario:notification.NombreBeneficiario,
      tipoCuentaBeneficiario:notification.TipoCuentaBeneficiario.@clave,
      cuentaBeneficiario:notification.CuentaBeneficiario,
      rfcCurpBeneficiario:notification.RfcCurpBeneficiario,
      conceptoPago:notification.ConceptoPago,
      referenciaNumerica:notification.ReferenciaNumerica,
      empresa:notification.Empresa
    )

    println "Command: ${command.dump()}"
    log.info "Command: ${command.dump()}"

    StpDeposit stpDeposit = command.createStpDeposit()
    println "StpDeposit: ${stpDeposit.dump()}"
    log.info "StpDeposit: ${stpDeposit.dump()}"

    stpDeposit.save()
    //actualizar saldo de cuenta bancaria stp
    //registrar cashin en M1
    stpDeposit
  }

}

