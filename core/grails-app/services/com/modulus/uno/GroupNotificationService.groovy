package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def createGroup(String notifyId, ArrayList<User> usersList){
    def newGroup = new GroupNotification(notificationId:notifyId, users:usersList)
    newGroup.save()
    newGroup
  }

  def updateGroupNotification(def groupId, ArrayList<User> newUserList, String newNotification){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
      groupNotification.users=newUserList
      groupNotification.notificationId = newNotification
      groupNotification.save()
  }

  def deleteGroupNotification(def groupId){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
    groupNotification.delete()
  }

}
