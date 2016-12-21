package com.modulus.uno

class BankTagLib {
  static namespace = "bankLib"
  static defaultEncodeAs = "raw"

  def bankService

  def getBankFromClabe = { attrs ->
    out << bankService.computeClabeAndGetBank(attrs.clabe)
  }

  def checkAccountForSTPAvailable = { attrs ->
    def company = Company.findById(session.company.toLong())
    def bankAccountWithConcentradora = company.banksAccounts.findAll { it -> it.concentradora == true }
    out << bankAccountWithConcentradora.size()

  }

}
