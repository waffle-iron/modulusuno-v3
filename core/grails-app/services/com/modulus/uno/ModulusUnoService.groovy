package com.modulus.uno

import grails.transaction.Transactional
import java.math.RoundingMode

@Transactional
class ModulusUnoService {

  def restService
  def grailsApplication

  static final feeType = [
    SaleOrder : "SALE_FEE",
    DepositOrder : "DEPOSIT_FEE",
    LoanOrder : "LOAN_FEE"
  ]

  static final commissionType = [
    SaleOrder : "VENTA",
    DepositOrder : "DEPOSITO",
    LoanOrder : "PRESTAMO"
  ]

  static final cashOutConcept = [
    PurchaseOrder : "PAGO A PROVEEDOR",
    CashOutOrder : "RETIRO"
  ]

  private FeeCommand createFeeCommandFromOrder(def order){
    def command = null
    String comType = commissionType."${order.class.simpleName}"
    Commission commission = order.company.commissions.find { com ->
        com.type == CommissionType."${comType}"
    }

    String fType = feeType."${order.class.simpleName}"

    BigDecimal amountFee = 0
    if (commission){
      if (commission.fee){
        amountFee = commission.fee * 1.0
      } else {
        amountFee = order.class.simpleName == "SaleOrder" ? order.total * (commission.percentage/100) : order.amount * (commission.percentage/100)
      }
      String uuid = order.company.accounts.first().timoneUuid
      command = new FeeCommand(uuid:uuid,amount:amountFee.setScale(2, RoundingMode.HALF_UP),type:fType)
    }
    command
  }

  def applyTransferFunds (TransferFundsCommand command) {
    restService.sendCommandWithAuth(command, grailsApplication.config.modulus.loanCreate)
  }

  def approveCashOutOrder(CashOutOrder cashOutOrder) {
    BigDecimal amount = cashOutOrder.amount.setScale(2, RoundingMode.HALF_UP)
    CashoutCommand command = new CashoutCommand(uuid:cashOutOrder.timoneUuid, clabe:cashOutOrder.account.clabe, bankCode:cashOutOrder.account.banco.bankingCode, amount:amount, speiFee:grailsApplication.config.speiFee, beneficiary:cashOutOrder.company.bussinessName, concept:cashOutConcept.CashOutOrder)
    restService.sendCommandWithAuth(command, grailsApplication.config.modulus.cashout)
  }

  def consultBalanceOfAccount(String account) {
    def command = new BalanceIntegratedCommand()
    command.uuid = account
    def response = restService.getOnModulus(command, grailsApplication.config.modulus.users)
    [response.balance, response.usd]
  }

  def consultBalanceIntegratorOfType(String type) {
    def balance = restService.getBalancesIntegrator(type, grailsApplication.config.modulus.integratorBalance)
    [balance.responseData.balance, balance.responseData.usd]
  }

  def createAccount(Company company,String email) {
    def command = new CreateAccountCommand(payerAccount:grailsApplication.config.modulus.stpPayerAccount,
                                           uuid:company.uuid,
                                           name:company.bussinessName,
                                           email:email)


    def accountResult = restService.sendCommandWithAuth(command, grailsApplication.config.modulus.users)
    accountResult
  }

  def generateACashinForIntegrated(DepositOrder order) {
    FeeCommand feeCommand = createFeeCommandFromOrder(order)
    if (!feeCommand){
      throw new CommissionException("No existe comisión para la operación")
    }

    CashinWithCommissionCommand command = new CashinWithCommissionCommand(uuid:order.company.accounts.first().timoneUuid,amount:order.amount.setScale(2, RoundingMode.HALF_UP), fee:feeCommand.amount, feeType:feeCommand.type)
    def cashinResult = restService.sendCommandWithAuth(command,grailsApplication.config.modulus.cashin)
    cashinResult
  }

  def cashInWithCommissionFromSaleOrder(SaleOrder order){
    FeeCommand feeCommand = createFeeCommandFromOrder(order)
    if (!feeCommand){
      throw new CommissionException("No existe comisión para la operación")
    }

    CashinWithCommissionCommand command = new CashinWithCommissionCommand(uuid:order.company.accounts.first().timoneUuid, amount:order.total.setScale(2, RoundingMode.HALF_UP), fee:feeCommand.amount, feeType:feeCommand.type)
    def cashinResult = restService.sendCommandWithAuth(command, grailsApplication.config.modulus.cashin)
    cashinResult
  }

  def payPurchaseOrder(PurchaseOrder order, PaymentToPurchase payment) {
    CashoutCommand command = new CashoutCommand(uuid:order.company.accounts?.first()?.timoneUuid, clabe:order.bankAccount.clabe, bankCode:order.bankAccount.banco.bankingCode, amount:payment.amount.setScale(2, RoundingMode.HALF_UP), speiFee:grailsApplication.config.speiFee, beneficiary:order.providerName, concept:cashOutConcept.PurchaseOrder)
    restService.sendCommandWithAuth(command, grailsApplication.config.modulus.cashout)
    command

  }

  def generedModulusUnoAccountByCompany(Company company, String email) {
    def accountInfo = createAccount(company,email)
    def modulusUnoAccount = saveAccountOfModulusUnoOfIntegrated(accountInfo,company)
    modulusUnoAccount
  }

  def getTransactionsInPeriodOfIntegrated(AccountStatementCommand command){
    restService.getTransactionsAccount(command)
  }

  def getTransactionsInPeriodOfIntegrator(AccountStatementCommand command){
    restService.getTransactionsIntegrator(command, grailsApplication.config.modulus.integratorTransactions)
  }

  def saveAccountOfModulusUnoOfIntegrated(def accountInformationJson,company) {
    ModulusUnoAccount account = new ModulusUnoAccount()
    account.account = accountInformationJson.account
    account.balance = accountInformationJson.balance
    account.integraUuid = accountInformationJson.integraUuid
    account.stpClabe = accountInformationJson.stpClabe
    account.timoneUuid = accountInformationJson.timoneUuid
    account.company = company
    account.save(failOnError:true)
  }

  def generateSubAccountStpForClient(CreateAccountCommand command) {
    def accountResult = restService.sendCommandWithAuth(command, grailsApplication.config.modulus.subAccountUser)
    log.info "New Sub Account Registered ${accountResult}"
    accountResult?.stpClabe
  }
}
