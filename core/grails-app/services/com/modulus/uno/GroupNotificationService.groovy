package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def corporateService

  def addNewGroup(def groupParams, def corporateId){

   /*
   log.info groupParams.userList.toString()
   log.info groupParams.nameGroup
   log.info groupParams.notificationId
   log.info corporateId
   */

   def corporateUsers = corporateService.findCorporateUsers(corporateId)

   //Obtener los usuarios
   /*
   corporateUsers.each{
    log.info "usuario comparado con los buscados${it}"
    if (users.contains(it.id))
      log.info "Se encontro que el usuario ${it.username} fue añadido al grupo"
   }
  */
   createGroup(groupParams.nameGroup, groupParams.notificationId, corporateUsers)
  }

  def createGroup(String groupName, String notifyId, def usersList){
    def newGroup = new GroupNotification(name:groupName, notificationId:notifyId, users:usersList)
    newGroup.save()
  }

  def updateGroupNotification(def groupId, String newNameGroup, ArrayList<User> newUserList, String newNotification){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
      groupNotification.name=newNameGroup
      groupNotification.users=newUserList
      groupNotification.notificationId = newNotification
      groupNotification.save(validate:false)
  }

  def deleteGroupNotification(def groupId){
    GroupNotification groupNotification = GroupNotification.findById(groupId)
    groupNotification.delete()
  }

}
