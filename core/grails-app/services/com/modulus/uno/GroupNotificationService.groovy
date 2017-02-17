package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def createGroup(String something, ArrayList<User> usersList){
    def newGroup = new GroupNotification(notificationId:something, users:usersList)
    newGroup.save()
    println "*"*30
    println newGroup.id
    println newGroup.notificationId
    println newGroup.users
    println "*"*30
    newGroup
  }

  def updateUsersGroup(def groupId, ArrayList<User> usersList){
    println "Actualizando lista de usuarios de un notification group"
  }

  def updateNotifyId(def groupId, ArrayList<User> usersList){
    println "Actualizando idNotify de un notification group exitstentw"
   }

}
