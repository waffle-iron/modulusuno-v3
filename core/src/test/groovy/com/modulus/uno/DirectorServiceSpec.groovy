package com.modulus.uno

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.lang.Void as Should
import grails.test.mixin.Mock

@TestFor(DirectorService)
@Mock([Company,Role,User,UserRoleCompany])
class DirectorServiceSpec extends Specification {

  Should "get the users of a company"(){
    given:"the company and the users of the company with their roles"
      Company company = createCompanyAndUsers()
    when:
      ArrayList<User> companyUsers = service.findUsersOfCompany(company.id)
    then:
      companyUsers.size() == 2
  }

  Should "get the users of a company by role"(){
    given:"the company"
      Company company = createCompanyAndUsers()
    and:"the role to looking for"
      String role = "ROLE_M1"
    when:
      ArrayList<User> companyUsersByRole = service.findUsersOfCompanyByRole(company.id,role)
    then:
      companyUsersByRole.size() == 1
  }

  private Company createCompanyAndUsers(){
    Company company = new Company(rfc:"JIGE930831GJ1",
                                  bussinessName:"Organization")
    company.save(validate:false)

    ArrayList<User> users = [new User(username:"egjimenezg"),
                             new User(username:"gamalieljg")]
    users.each{ user ->
      user.save(validate:false)
    }

    ArrayList<Role> roles = [new Role(authority:"ROLE_M1"),
                             new Role(authority:"ROLE_CORPORATIVE")]

    roles.each{ role -> role.save(validate:false) }

    users.eachWithIndex{ user,index ->
      UserRoleCompany userRole = new UserRoleCompany(user:user,company:company)
      userRole.addToRoles(roles[index])
      userRole.save()
    }

    company
  }

}
