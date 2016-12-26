package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class OrganizationService {

  def roleService
  
  def createRolesForUserInCompanies(String username, Map rolesForCompanies){
    User user = User.findByUsername(username)
    rolesForCompanies.each { companyName, rolesForThisCompany ->
      Company company = Company.findByBussinessName(companyName)
      rolesForThisCompany.each { role, value ->
        roleService.createRoleForUserAtThisCompany(role, user, company)
      }
    }
    user
  }

}
