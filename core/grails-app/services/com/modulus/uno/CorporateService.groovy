package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class CorporateService {

  RecoveryService recoveryService
  def springSecurityService

  def addCompanyToCorporate(Corporate corporate, Company company) {
    corporate.addToCompanies(company)
    corporate.save()
    corporate
  }

  def saveNewCorporate(Corporate corporate) {
    corporate.save()
  }

  def createRoute53(Corporate corporate) {
    //checar esta ligas http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
    true
  }


  def createAVirtualHostNginx(Corporate corporate) {
    //Esta en si en manejo del nginx pero deveras de chacar muy bien los puestos que se van usando tanto para cada nuevo coorporativo
    true
  }

  Corporate findCorporateOfUser(User user){
    Corporate.createCriteria().get {
      users {
        eq 'username', user.username
      }
    }
  }

  User findCorporateUserOfCompany(Long companyId){
    ArrayList<User> users

    Corporate corporate = Corporate.createCriteria().get{
      companies{
        eq("id",companyId)
      }
    }

    users = corporate.users ?: []
    users = users.findAll{ user -> user.getAuthorities()*.authority.contains('ROLE_CORPORATIVE') }
    users[0]
  }

  Corporate addUserToCorporate(Long corporateId,User user){
    Corporate corporate = Corporate.get(corporateId)
    corporate.addToUsers(user)
    corporate.save()
    corporate
  }

  ArrayList<User> findCorporateUsers(Long corporateId){
    Corporate corporate = Corporate.get(corporateId)
    ArrayList<User> corporateUsers = corporate.users

    User user = springSecurityService.currentUser
    ArrayList<String> currentUserAuthorities = user.getAuthorities()*.authority

    if(currentUserAuthorities.contains("ROLE_M1"))
      corporateUsers = corporateUsers.findAll{ _user -> _user.getAuthorities()*.authority.contains("ROLE_CORPORATIVE") }
    else
      corporateUsers = corporateUsers.findAll{ _user -> ["ROLE_M1","ROLE_CORPORATIVE"].every{ !(it in _user.getAuthorities()*.authority) } }

    corporateUsers
  }

}
