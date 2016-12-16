package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class MovimientosBancariosService {

  def createMovimeintosBancariosFromList(def listRowElements, BankAccount bankAccount) {
    if (!isValidList(listRowElements))
      return ""
    MovimientosBancariosCommand command = new MovimientosBancariosCommand()
    command.concept = listRowElements.get(1)
    command.reference = listRowElements.get(2)
    command.dateEvent = listRowElements.get(0)
    command.amount = listRowElements.get(3) ?: listRowElements.get(4)
    command.debito = listRowElements.get(3)?:0
    command.credito = listRowElements.get(4)?:0
    if (command.validate()) {
      MovimientosBancarios movimiento = command.createObjectByRow()
      movimiento.cuenta = bankAccount
      movimiento.save()
      return movimiento
    }
    else
      return command.errors
  }

  private def isValidList(def elementos) {
    def resultEachList = []
    elementos.each { row ->
      if (row.length() == 0)
        resultEachList << true
    }
    if (resultEachList.size() > 2)
      return false
    else
      return true
  }

}
