package com.modulus.uno

import spock.lang.Specification
import spock.lang.Unroll
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(Corporate)
@Mock([Company,User,Commission])
class CorporateSpec extends Specification {

  @Unroll
  void """ create a corporate and validate """() {
    given:
      def corporate = new Corporate()
    when:
      corporate.nameCorporate = corporateName
      corporate.corporateUrl = urlCorporate
    then:
      result == corporate.validate()
    where:
      corporateName | urlCorporate || result
      "says"        | "says-inc"   || true
      "makingdevs"  | "makingdevs" || true
      null          | "algo"       || false
      "algo"        | null         || false
      null          | null         || false
  }

  void "create a corporate and add one user, one company and one commission"() {
    given:
      Corporate corporate = new Corporate()
      corporate.nameCorporate = "makingdevs"
      corporate.corporateUrl = "makingdevs"
      corporate.save()
    and:
      def user = new User().save(validate:false)
      def company = new Company().save(validate:false)
      def commission = new Commission().save(validate:false)
    when:
      corporate.addToUsers(user)
      corporate.addToCompanies(company)
      corporate.addToCommissions(commission)
      corporate.save()
    then:
      corporate.id == 1
      corporate.companies.size() == 1
      corporate.users.size() == 1
      corporate.commissions.size() == 1
  }

}
