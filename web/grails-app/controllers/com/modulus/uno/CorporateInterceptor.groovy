package com.modulus.uno

class CorporateInterceptor {

  def springSecurityService
  def corporateService

  CorporateInterceptor(){
    match(controller:"corporate")
  }

  boolean before() {
    if(!session.corporate){
      session.corporate = corporateService.findCorporateOfUser springSecurityService.currentUser
    }
    println session
    true
  }

  boolean after() {
    model.corporate = corporateService.findCorporateOfUser springSecurityService.currentUser
    true
  }

  void afterView() {
    // no-op
  }
}
