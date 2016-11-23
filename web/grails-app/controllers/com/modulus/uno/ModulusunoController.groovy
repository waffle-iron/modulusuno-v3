package com.modulus.uno

class ModulusunoController {

  def index(){
    render view:'detail'
  }

  def logout(){
    redirect controller:'logout'
  }

}
