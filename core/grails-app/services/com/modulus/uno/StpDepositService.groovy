package com.modulus.uno

import grails.transaction.Transactional
import java.math.RoundingMode

@Transactional
class StpDepositService {

  def notificationDepositFromStp(String xmlNotif) {
    StpDeposit stpDeposit = saveNotification(xmlNotif)
    //actualizar saldo de cuenta bancaria stp
    //registrar cashin en M1
    //crear la notificación de pago para concialiar factura
    stpDeposit
  }

  private StpDeposit saveNotification(String xml) {
    def notification = new XmlSlurper().parseText(xml)
    StpDepositCommand command = createStpDepositCommand(notification)
    StpDeposit stpDeposit = command.createStpDeposit()
    stpDeposit.save()
    stpDeposit
  }

  private StpDepositCommand createStpDepositCommand(def notification) {
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
  }
}

