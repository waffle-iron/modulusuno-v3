package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class CorporateService {

  RecoveryService recoveryService
  UserService userService

  def addCompanyToCorporate(Corporate corporate, Company company) {
    corporate.addToCompanies(company)
    corporate.save()
    corporate
  }

  def addNewUserToCorporate(Long corporateId, User user,String authority = ""){
    Role role = Role.findWhere(authority: authority)
    addUserToCorporate(corporateId,user,role)
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

  Corporate addUserToCorporate(Long corporateId,User user,Role role = null){
    if(role)
      user = userService.setAuthorityToUser(user,role)
    Corporate corporate = Corporate.get(corporateId)
    corporate.addToUsers(user)
    corporate.save()
    corporate
  }

}
