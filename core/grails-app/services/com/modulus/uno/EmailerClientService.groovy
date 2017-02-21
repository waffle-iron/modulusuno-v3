package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class EmailerClientService {

  WsliteConnectionService wsliteConnectionService

  def getEmailerStorage(){
    def result = wsliteConnectionService.get("http://emailerv2.modulusuno.com", "/show")
  }

  def getEmailerList(ArrayList emailerStorage){
    def emailerList
    emailerList = emailerStorage.collect{ emailer ->
      ["id":emailer._id, "subject":emailer.subject]
    }
    emailerList
  }

}
