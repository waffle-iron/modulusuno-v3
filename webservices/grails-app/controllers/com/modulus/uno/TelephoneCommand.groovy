package com.modulus.uno

import grails.validation.Validateable

class TelephoneCommand implements Validateable {
  String number
  String extension
  TelephoneType type

  Telephone createTelephone() {
    new Telephone(
      number: this.number,
      extension: this.extension,
      type: TelephoneType."${this.type}"
    )
  }
}
