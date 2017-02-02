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
    executeCashinIntoAccountM1(stpDeposit)
    generatePaymentToConciliateBill(stpDeposit)
  }

  private def executeCashinIntoAccountM1(StpDeposit stpDeposit) {
    ModulusUnoAccount m1Account = ModulusUnoAccount.findByStpClabe(stpDeposit.accountBeneficiary)
    if (m1Account) {
      DepositOrder depositOrder = new DepositOrder()
      depositOrder.amount = stpDeposit.amount
      depositOrder.company = m1Account.company
      modulusUnoService.generateACashinForIntegrated(depositOrder)
    }
  }

  private def generatePaymentToConciliateBill(StpDeposit stpDeposit) {
    ClientLink client = ClientLink.findByStpClabe(stpDeposit.accountBeneficiary)
    if (client) {
      Payment payment = new Payment(amount:stpDeposit.amount, rfc:client.clientRef, company:client.company)
      payment.save()
    }
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

