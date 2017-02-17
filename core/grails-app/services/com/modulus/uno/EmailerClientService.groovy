package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class EmailerClientService {

  def getEmailerList(ArrayList emailerStorage){
    def emailerList
    emailerList = emailerStorage.collect{ emailer ->
      ["id":emailer._id, "subject":emailer.subject]
    }
    emailerList
  }

}
