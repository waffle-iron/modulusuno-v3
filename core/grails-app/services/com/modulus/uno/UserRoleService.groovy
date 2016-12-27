package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class UserRoleService {

  User deleteUserRolesForUser(User user){
    List<UserRole> userRoles = UserRole.findAllByUser(user)
    userRoles.each{ UserRole userRole -> 
      userRole.delete()
    }
  }

}
