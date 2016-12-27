package com.modulus.uno

class CorporateInterceptor {

  def springSecurityService
  def corporateService

  CorporateInterceptor(){
    match(controller:"corporate")
  }

  boolean before() {
    if(!session.corporate){
      User user = springSecurityService.currentUser
      if(!user)
        return

      session.corporate = corporateService.findCorporateOfUser user
    }
    true
  }

  boolean after() {
    if(session.corporate && model)
      model.corporate = session.corporate
    true
  }

  void afterView() {
    // no-op
  }
}
