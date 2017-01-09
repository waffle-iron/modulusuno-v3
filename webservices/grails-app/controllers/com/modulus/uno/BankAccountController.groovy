package com.modulus.uno

import grails.transaction.Transactional
import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*

@Api
class BankAccountController {

  def bankAccountService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  @SwaggyList
  def index() {
    respond BankAccount.list(), status: 200, formats: ['json']
  }

  @SwaggyShow
  def show() {
    def bankAccount = BankAccount.findbyId(params.id)
    if (bankAccount)
      respond bankAccount, status: 200, formats: ['json']
    else
      response.sendError(404,"BankAccount not Exist or need send a id" )
  }

  @SwaggySave(extraParams = [
      @ApiImplicitParam(name = 'accountNumber', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'branchNumber', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'clabe', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'bank', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'businessEntityId', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'companyId', value = '', dataType = 'number',paramType = 'form'),
      ])
  def save(BankAccountCommand command) {
    def bankAccount = command.createBankAccount()
    bankAccount.banco = Bank.findByBankingCodeLike("%${command.bank}%")
    if(bankAccount.hasErrors()) {
      respond bankAccount.errors, status: 404, formats: ['json']
      return
    }

    bankAccountService.addBankAccountToRelationShip(bankAccount,params.companyId?.toLong()?:0, params.businessEntityId?.toLong()?:0, params)
    if (bankAccount)
      respond bankAccount, status: 201, formats: ['json']
    else
      response.sendError(404,"BankAccount not create or need send a id entity relationship" )
  }

}
