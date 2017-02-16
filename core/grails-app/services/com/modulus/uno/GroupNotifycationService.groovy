package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class GroupNotifycationService {

  def createNewGroup(def id, ArrayList<User>){
    println "Creando notification group"
  }

  def updateUsersGroup(def groupId, ArrayList<User>){
    println "Actualizando lista de usuarios de un notification group"
  }

  def updateNotifyId(def groupId, ArrayList<User>){
    println "Actualizando idNotify de un notification group exitstentw"
   }
}
