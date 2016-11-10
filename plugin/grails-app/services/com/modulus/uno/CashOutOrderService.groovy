package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class CashOutOrderService {

	def restService
  def grailsApplication
  def modulusUnoService
	def companyService

	def addAutorizationToCashoutOrder(CashOutOrder cashOutOrder, User user) {
		def authorization = new Authorization(user: user)
		cashOutOrder.addToAuthorizations(authorization)
		cashOutOrder.save()
	}

	def authorizeAndDoCashOutOrder(CashOutOrder cashOutOrder) {
		modulusUnoService.approveCashOutOrder(cashOutOrder)
		def legalRepresentatives = cashOutOrder.company.legalRepresentatives
		def message = "Se ha generado la orden de retiro por ${cashOutOrder.amount}, para la cuenta ${cashOutOrder.account} con fecha de ${new Date().format('dd/MMM/yyyy')}"
		sendNotificationToUsers(legalRepresentatives,cashOutOrder,message)
		cashOutOrder
	}

  BigDecimal getTotalOrdersAuthorizedOfCompany(Company company){
    CashOutOrder.findAllByCompanyAndStatus(company, CashOutOrderStatus.AUTHORIZED).amount.sum()
  }

	def isAvailableForAuthorize(CashOutOrder cashOutOrder) {
		(cashOutOrder.authorizations?.size() ?: 0) == cashOutOrder.company.numberOfAuthorizations
	}

  def notificationToAuthorizersAndLegalRepresentatives(CashOutOrder cashOutOrder) {
  	def authorizers = companyService.getAuthorizersByCompany(cashOutOrder.company)
  	def legalRepresentatives = cashOutOrder.company.legalRepresentatives
  	def message = "La empresa ${cashOutOrder.company.toString()} desea realizar una orden de retiro por ${cashOutOrder.amount} para cuenta ${cashOutOrder.account.toString()}"
  	sendNotificationToUsers(authorizers,cashOutOrder,message)
  	sendNotificationToUsers(legalRepresentatives,cashOutOrder,message)
  	cashOutOrder.status = CashOutOrderStatus.IN_PROCESS
  	cashOutOrder.save()
  	cashOutOrder
  }

	private def createEmailNotificacionToUser(Company company,String url,User user,String message) {
		def emailNotificationAdminCommand = new EmailNotificationToIntegratedCommand()
		emailNotificationAdminCommand.emailResponse = user.profile.email
		emailNotificationAdminCommand.nameCompany = company.toString()
		emailNotificationAdminCommand.message = message
		emailNotificationAdminCommand.url = url
		emailNotificationAdminCommand
	}

  private def sendNotificationToUsers(def userList, CashOutOrder cashOutOrder,String message) {
  	def url = "${grailsApplication.config.cashOutOrder.url}${cashOutOrder.id}"
  	userList.each {user ->
  		def messageCommand = createEmailNotificacionToUser(cashOutOrder.company,url, user,message)
  		restService.sendCommand(messageCommand, grailsApplication.config.emailer.notificationIntegrated)
  	}
  }

  def getCashoutOrderStatus(String status){
    def cashoutOrderStatuses = []
    cashoutOrderStatuses = Arrays.asList(CashOutOrderStatus.values())
    if (status){
      def listStatus = status.tokenize(",")
      cashoutOrderStatuses = listStatus.collect { it as CashOutOrderStatus }
    }

    cashoutOrderStatuses
  }


  def getCashoutOrdersToList(Long company, params){
    def statusOrders = getCashoutOrderStatus(params.status)
    def cashoutOrders = [:]
    if(company){
      cashoutOrders.list = CashOutOrder.findAllByCompanyAndStatusInList(Company.get(company), statusOrders)
      cashoutOrders.items = CashOutOrder.countByCompanyAndStatusInList(Company.get(company), statusOrders)
    } else{
      cashoutOrders.list = CashOutOrder.findAllByStatusInList(statusOrders)
      cashoutOrders.items = CashOutOrder.countByStatusInList(statusOrders)
    }
    cashoutOrders
  }

}
