package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class OrganizationService {

  def roleService
  
  def createRolesForUserInCompanies(String username, Map rolesForCompanies){
    User user = User.findByUsername(username)
    rolesForCompanies.each { companyName, rolesForThisCompany ->
      Company company = Company.findByBussinessName(companyName)
      List<Role> roles = rolesForThisCompany.collect { role, value ->
        Role.findByAuthority(role)
      }
      roleService.createRolesForUserAtThisCompany(roles, user, company)
    }
    user
  }

}
