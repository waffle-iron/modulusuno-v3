package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class OrganizationService {

  def roleService
  
  User createRolesForUserInCompanies(User user, Map rolesForCompanies){
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

  User deleteRolesForUserInCompanies(User user, def companies){
    companies.each{ Company company ->
      roleService.deleteRolesForUserAtThisCompany(user, company)
    }
    user
  }

  User updateRolesForUserInCompanies(String username, Map rolesForCompanies){
    User user = User.findByUsername(username)
    def companies = rolesForCompanies.collect { companyName, rolesForThisCompany ->
      Company company = Company.findByBussinessName(companyName)
    }
    deleteRolesForUserInCompanies(user,companies)
    createRolesForUserInCompanies(user,rolesForCompanies)
  }

  List<Company> findAllCompaniesOfUser(User user){
    List<UserRoleCompany> userRolesCompanies = UserRoleCompany.findAllByUser(user)
    userRolesCompanies*.company
  }

}
