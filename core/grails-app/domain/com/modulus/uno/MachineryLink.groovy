package com.modulus.uno

class MachineryLink {

  Long machineryRef
  String type

  Date dateCreated
  Date lastUpdated

  static hasMany = [machineries:Machinery]

  static constraints = {
    machineryRef min:0L
    type blank:false
  }

}