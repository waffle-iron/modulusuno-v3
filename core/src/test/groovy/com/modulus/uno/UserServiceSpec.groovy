package com.modulus.uno

import spock.lang.Specification
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import java.lang.Void as Should

@TestFor(UserService)
@Mock([UserRole])
class UserServiceSpec extends Specification {

  def service = new UserService()
  def recoveryService = Mock(RecoveryService)

  def setup(){
    service.recoveryService = recoveryService
  }

  void "should create an user"() {
  given: "An user and email"
    def user = Mock(User)
    def email = 'josdem@email.com'
  and: "A role"
    def role = Mock(Role)
  and: "A profile"
    def profile = Mock(Profile)
    profile.email >> email
    user.profile >> profile
    Profile.metaClass.static.findByEmail = { null }
  when: "We assing values to command"
    service.save(user, role)
  then:"We validate command"
    user.profile >> profile
    1 * user.save()
    1 * recoveryService.sendConfirmationAccountToken(email)
  }

  Should "save an user without Role"(){
    given:"the user"
      User user = Mock(User)
      user.username="egjimenezg"
      user.password="1234567890"
    and:"the profile"
      Profile profile = Mock(Profile)
      profile.name = 'Gamaliel'
      profile.lastName = 'Jimenez'
      profile.motherLastName = 'Garcia'
      profile.email = 'egjimenezg@gmail.com'
      profile.rfc = 'ABCD900212NZ1'
      profile.curp = 'JIGE930831HMCMRD01'
      profile.trademark = 'XXXX'
      profile.fullName = 'Gamaliel Jimenez Garcia'
    when:
      service.createUserWithoutRole(user,profile)
    then:
      1 * user.save()
      1 * recoveryService.sendConfirmationAccountToken(profile.email)
  }

}
