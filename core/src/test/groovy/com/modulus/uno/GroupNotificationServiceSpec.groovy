package com.modulus.uno

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

@TestFor(GroupNotificationService)
@Mock([GroupNotification, User])
class GroupNotificationServiceSpec extends Specification {

    def setup() {
    }

    def "Create a new notification group"() {
      given:"A users list"
        def user1= new User(username:"User1",enabled:true,
        profile:new Profile(name:"User1", email:"user1@me.com")).save(validate:false)
        def user2= new User(username:"User2",enabled:true,
        profile:new Profile(name:"User2", email:"user2@me.com")).save(validate:false)
        def user3= new User(username:"User3", enabled:true,
        profile:new Profile(name:"User3", email:"user3@me.com")).save(validate:false)
        def user0= new User(username:"User0", enabled:true,
        profile:new Profile(name:"User0", email:"user0@me.com")).save(validate:false)
        ArrayList<User> userList = [user1, user2, user3]
      and:"a notification id"
        def notificationId = "586d4944e1d4ae54524dd622"
      when:"we want to save the group"
        def firstUserNotificationGroup = service.createGroup(notificationId, userList)
      then:"we should get"
        firstUserNotificationGroup.id == 1
        firstUserNotificationGroup.users == [user1, user2, user3]
        firstUserNotificationGroup.users != [user1, user2, user0]
        firstUserNotificationGroup.notificationId == "586d4944e1d4ae54524dd622"
    }

    def "Update a notification group"() {
      given:"A groupNotification"
        GroupNotification firstGroup = createFirstUserGroup()
      and:"A new users list for update"
        def user4= new User(username:"newUser1",enabled:true,
        profile:new Profile(name:"newUser1", email:"user1new@me.com")).save(validate:false)
        def user5= new User(username:"newUser2",enabled:true,
        profile:new Profile(name:"newUser2", email:"user2new@me.com")).save(validate:false)
        def user6= new User(username:"newUser3", enabled:true,
        profile:new Profile(name:"newUser3", email:"user3new@me.com")).save(validate:false)
        ArrayList<User> newUserList = [user4, user5, user6]
      and:"a  new notification id"
        def newNotificationId = "586d4944e1d4ae5diamon666"
      when:"we want to update the userList and the notificationId"
        service.updateGroupNotification(firstGroup.id, newUserList, newNotificationId)
      then:"we should get"
        firstGroup.users == newUserList
        firstGroup.notificationId == newNotificationId
    }

    def "Delete a notification group"(){
      given:"A group notification"
        GroupNotification firstGroup = createFirstUserGroup()
      when:"we want to delete a group notification"
        service.deleteGroupNotification(firstGroup.id)
      then:"We shouldn't have any group notification"
        assert !GroupNotification.findById(firstGroup.id)
    }

      private createFirstUserGroup(){
        def user1= new User(username:"User1",enabled:true,
        profile:new Profile(name:"User1", email:"user1@me.com")).save(validate:false)
        def user2= new User(username:"User2",enabled:true,
        profile:new Profile(name:"User2", email:"user2@me.com")).save(validate:false)
        def user3= new User(username:"User3", enabled:true,
        profile:new Profile(name:"User3", email:"user3@me.com")).save(validate:false)
        ArrayList<User> userList = [user1, user2, user3]
        def firstNotificationGroup = new GroupNotification(notificationId: "763gytdg327fgfg67fv5f", users: userList)
        firstNotificationGroup.save(validate:false)
        firstNotificationGroup

    }

}
