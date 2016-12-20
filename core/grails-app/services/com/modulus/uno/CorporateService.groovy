package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class CorporateService {

  def addCompanyToCorporate(Corporate corporate, Company company) {
    corporate.addToCompanies(company)
    corporate.save()
    corporate
  }

  def addUserToCorporate(Corporate corporate, User user) {
    corporate.addToUsers(user)
    corporate.save()
    setAuthorityToUser(user)
    corporate
  }

  def createNewCorporate(String name, String url) {
    Corporate corporate = new Corporate(nameCorporate:name, corporateUrl:url).save()
    corporate
  }


  def createRoute53(Corporate corporate) {
    //checar esta ligas http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html
    true
  }


  def createAVirtualHostNginx(Corporate corporate) {
    //Esta en si en manejo del nginx pero deveras de chacar muy bien los puestos que se van usando tanto para cada nuevo coorporativo
    true
  }

  ArrayList<Corporate> findUserCorporations(User user){
    Corporate.createCriteria().list {
      users {
        eq 'username', user.username
      }
    }
  }

  private def setAuthorityToUser(User user) {
    def userRole = Role.findWhere(authority: 'ROLE_CORPORATIVE')
    UserRole.create user, userRole, true
    user.save()
  }


}
