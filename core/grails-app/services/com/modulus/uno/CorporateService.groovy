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

  def addUserToCorporate(Long corporateId, User user) {
    User createdUser = userService.createUserWithoutRole(user,user.profile)
    Role role = Role.findWhere(authority: 'ROLE_CORPORATIVE')
    createdUser = userService.setAuthorityToUser(createdUser,role)
    Corporate corporate = Corporate.get(corporateId)
    corporate.addToUsers(createdUser)
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

  private User setAuthorityToUser(def user) {
    def userRole = Role.findWhere(authority: 'ROLE_CORPORATIVE')
    UserRole.create user, userRole, true
    user.save()
  }


}
