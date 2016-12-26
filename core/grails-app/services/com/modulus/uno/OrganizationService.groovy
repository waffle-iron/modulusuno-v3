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
      if (rolesForThisCompany) {
        roleService.createRolesForUserAtThisCompany(roles, user, company) 
      }
    }
    user
  }

  List<UserRoleCompany> findRolesForUserInCompanies(String username, Corporate corporate){
    User user = User.findByUsername(username)
    corporate.companies.collect{ Company company ->
      roleService.findRolesForUserAtThisCompany(user, company)
    }.findAll{ it }
  }

}
