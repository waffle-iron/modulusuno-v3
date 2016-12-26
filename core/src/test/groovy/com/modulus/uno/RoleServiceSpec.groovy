package com.modulus.uno

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.lang.Void as Should
import grails.test.mixin.Mock

@TestFor(RoleService)
@Mock([User, Company, Role, UserRoleCompany])
class RoleServiceSpec extends Specification {

  Should "Create the roles for user at one company"(){
    given:
      List<Role> roles = ["ROLE_LEGAL_REPRESENTATIVE_EJECUTOR","ROLE_FICO_VISOR"].collect { String r ->
        new Role(r).save(validate:false)
      }
      Company company = new Company(bussinessName:"makingdevs").save(validate:false)
      User user = new User("user","user")
      user.save(validate:false)
    when:
      UserRoleCompany userRoleByCompany = service.createRolesForUserAtThisCompany(roles,user,company)
    then:
      userRoleByCompany.id
      userRoleByCompany.company == company
      userRoleByCompany.user == user
      userRoleByCompany.roles.size() == 2
  }

}