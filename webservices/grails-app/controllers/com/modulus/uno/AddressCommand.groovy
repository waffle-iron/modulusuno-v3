package com.modulus.uno
import grails.validation.Validateable

class AddressCommand implements Validateable {
  String street
  String streetNumber
  String suite
  String zipCode
  String colony
  String country
  String city
  String town
  String federalEntity
  String addressType

  Address createAddress() {
    new Address(
      street: this.street,
      streetNumber: this.streetNumber,
      suite: this.suite,
      zipCode: this.zipCode,
      colony: this.colony,
      country: this.country,
      city: this.city,
      town: this.town,
      federalEntity: this.federalEntity,
      addressType: AddressType."${this.addressType}"
    )
  }
}
