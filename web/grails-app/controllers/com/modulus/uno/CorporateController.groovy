package com.modulus.uno

class CorporateController {

  def create(){
    respond new Corporate(params)
  }

}
