package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*

@Api()
class AddressController {

  static allowedMethods = [save: "POST", update: "POST"]

  def addressService

  @SwaggyList
  def index() {
    respond Address.list(), status: 200, formats: ['json']
  }

  @SwaggyShow
  def show() {
    def address = Address.findById(params.id)
    if (address)
      respond address, status: 200, formats: ['json']
    else
      response.sendError(404,"Address not Exist or need send a id" )
  }

  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = 'street', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'streetNumber', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'suite', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'zipCode', value = '', dataType = 'number',paramType = 'form'),
    @ApiImplicitParam(name = 'colony', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'country', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'city', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'town', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'federalEntity', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'addressType', value = 'FISCAL,SOCIAL,COMERCIAL', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'businessEntityId', value = '', dataType = 'string',paramType = 'form'),
    @ApiImplicitParam(name = 'companyId', value = '', dataType = 'string',paramType = 'form')
  ])
  def save(AddressCommand command) {
    def address = command.createAddress()

    if (address.hasErrors()) {
      respond address.errors, status: 404, formats: ['json']
      return
    }
    def domain = addressService.createAddressForAObject(address, params.long('businessEntityId'), params.long('companyId'))
    if (domain)
      respond domain, status: 201, formats: ['json']
    else
      response.sendError(404,"Address not create or need send a id entity relationship" )
  }

}
