package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class StpDepositService {

  def modulusUnoService

  def notificationDepositFromStp(String xmlNotif) {
    StpDeposit stpDeposit = saveNotification(xmlNotif)
    StpDepositStatus status = defineStpDepositStatus(stpDeposit)
    if (status == StpDepositStatus.ACEPTADO) {
      processStpDeposit(stpDeposit)
    }
    [clave:stpDeposit.operationNumber, rastreo:stpDeposit.tracingKey, estatus:status]
  }

  private def processStpDeposit(StpDeposit stpDeposit) {
    ModulusUnoAccount m1Account = ModulusUnoAccount.findByStpClabe(stpDeposit.accountBeneficiary)
    ClientLink client = ClientLink.findByStpClabe(stpDeposit.accountBeneficiary)
    executeCashinIntoAccountM1(m1Account, client, stpDepositi)
    generatePaymentToConciliateBill(m1Account, client, stpDeposit)
  }

  private def executeCashinIntoAccountM1(ModulusUnoAccount m1Account, ClientLink client, StpDeposit stpDeposit) {
    DepositOrder depositOrder = new DepositOrder()
    depositOrder.amount = stpDeposit.amount
    depositOrder.company = m1Account ? m1Account.company : client.company
    modulusUnoService.generateACashinForIntegrated(depositOrder)
  }

  private def generatePaymentToConciliateBill(ModulusUnoAccount m1Account, ClientLink client, StpDeposit stpDeposit) {
    Payment payment = new Payment(amount:stpDeposit.amount)
    payment.rfc = client ? client.clientRef : null
    payment.company = m1Account ? m1Account.company : client.company
    payment.save()
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

  private StpDepositStatus defineStpDepositStatus(StpDeposit stpDeposit) {
    def status = StpDepositStatus.RECHAZADO
    //checar si la notificación ya ha sido enviada anteriormente
    def stpDeposits = StpDeposit.findAllByOperationNumberAndTracingKeyAndIdNotEqualAndStatusNotEqual(stpDeposit.operationNumber, stpDeposit.tracingKey, stpDeposit.id, StpDepositStatus.RECIBIDO)
    if (stpDeposits) {
      StpDeposit lastDeposit = stpDeposits.sort { it.dateCreated }.last()
      status = lastDeposit.status
    } else {
      //validar si cuenta es de company o client
      ModulusUnoAccount account = ModulusUnoAccount.findByStpClabe(stpDeposit.accountBeneficiary)
      ClientLink client = ClientLink.findByStpClabe(stpDeposit.accountBeneficiary)
      if (account || client) {
        status = StpDepositStatus.ACEPTADO
      }
    }
    stpDeposit.status = status
    stpDeposit.save()
    status
  }

}

