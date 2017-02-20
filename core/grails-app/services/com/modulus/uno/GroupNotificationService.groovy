package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def createGroup(String groupName, String notifyId, ArrayList<User> usersList){
    def newGroup = new GroupNotification(name:groupName, notificationId:notifyId, users:usersList)
    newGroup.save()
    newGroup
  }

  def updateGroupNotification(def groupId, String newNameGroup, ArrayList<User> newUserList, String newNotification){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
      groupNotification.name=newNameGroup
      groupNotification.users=newUserList
      groupNotification.notificationId = newNotification
      groupNotification.save()
  }

  def deleteGroupNotification(def groupId){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
    groupNotification.delete()
  }

}
