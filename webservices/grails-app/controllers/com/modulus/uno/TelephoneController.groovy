package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*

@Api
class TelephoneController {

  static allowedMethods = [saveForCompany: "POST"]

  def telephoneService

  @SwaggyList
  def index(Integer max) {
    respond Telephone.list(),status: 200, formats: ['json']
  }

  def show() {
    def telephone = Telephone.findById(params.id)
    if (telephone)
      respond telephone, status: 200, formats: ['json']
    else
      response.sendError(404,"Telephone not Exist or need send a id" )
  }

  @SwaggySave(extraParams = [
      @ApiImplicitParam(name = 'number', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'extension', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'type', value = 'TRABAJO', dataType = 'string',paramType = 'form'),
      @ApiImplicitParam(name = 'companyId', value = '', dataType = 'number',paramType = 'form')
      ])
  def saveForCompany(TelephoneCommand command) {

    def telephone = command.createTelephone()
    if (telephone.hasErrors()) {
      respond telephone.errors, view:'create'
      return
    }

    Company company = Company.findById(params.companyId.toLong())
    telephoneService.saveForCompany(telephone, company)

    if (telephone.id)
      respond telephone, status: 201, formats: ['json']
    else
      response.sendError(404,"Telephone not create or need send a id company relationship" )

  }


}
