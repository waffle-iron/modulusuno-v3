package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def createGroup(String notifyId, ArrayList<User> usersList){
    def newGroup = new GroupNotification(notificationId:notifyId, users:usersList)
    newGroup.save()
    newGroup
  }

  def updateUsersGroup(def groupId, ArrayList<User> newUserList){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
      groupNotification.users=newUserList
      groupNotification.save()
  }

  def updateNotifyId(def groupId, String newNotificationId){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
      groupNotification.notificationId = newNotificationId
      groupNotification.save()
   }

}
