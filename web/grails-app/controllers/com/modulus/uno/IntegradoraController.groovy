package com.modulus.uno

class IntegradoraController {

  def index(){
    render view:'detail'
  }

  def logout(){
    redirect controller:'logout'
  }

}
