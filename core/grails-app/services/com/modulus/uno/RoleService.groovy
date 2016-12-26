package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class RoleService {

    UserRoleCompany createRolesForUserAtThisCompany(List<Role> roles, User user, Company company) {
      UserRoleCompany userRoleByCompany = new UserRoleCompany(user:user,company:company)
      roles.collect{ Role role ->
        userRoleByCompany.addToRoles(role)
      }
      userRoleByCompany.save()
      userRoleByCompany
    }
}
