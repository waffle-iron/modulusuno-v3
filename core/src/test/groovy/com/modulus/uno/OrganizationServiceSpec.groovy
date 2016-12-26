package com.modulus.uno

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.lang.Void as Should
import grails.test.mixin.Mock

@TestFor(OrganizationService)
@Mock([User, Company])
class OrganizationServiceSpec extends Specification {

  Should "Should create roles for user in a companies"(){
    given:
      Company c1 = new Company(bussinessName:"makingdevs")
      Company c2 = new Company(bussinessName:"talachero")
      Company c3 = new Company(bussinessName:"farloperos")
      [c1,c2,c3]*.save(validate:false)
      User user = new User("user","user")
      user.save(validate:false)
      Map rolesForCompanies = [
        talachero:[
          ROLE_LEGAL_REPRESENTATIVE_EJECUTOR:"on", 
          ROLE_FICO_VISOR:"on"], 
        makingdevs:[
          ROLE_LEGAL_REPRESENTATIVE_EJECUTOR:"on", 
          ROLE_FICO_VISOR:"on"
        ]
      ]
    and:
      def roleServiceMock = Mock(RoleService){
        4 * createRoleForUserAtThisCompany(_,user, _)
      }
      service.roleService = roleServiceMock
    when:
      user = service.createRolesForUserInCompanies("user",rolesForCompanies)
    then:
      assert user
  }

}
