package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotificationService {

  def createNewGroup(def id, ArrayList<User> usersList){
    println "Creando notification group"
  }

  def updateUsersGroup(def groupId, ArrayList<User> usersList){
    println "Actualizando lista de usuarios de un notification group"
  }

  def updateNotifyId(def groupId, ArrayList<User> usersList){
    println "Actualizando idNotify de un notification group exitstentw"
   }

}
