package com.modulus.uno

class RequestCompanyController {

  def springSecurityService
  def requestIntegratedService

  def create() {
    def company = Company.findById(session.company.toLong())
    def currentUser = springSecurityService.currentUser
    requestIntegratedService.sendNotificationsOFCompanyToIntegrated(company, currentUser)
    redirect controller:'integradora'
  }

}
