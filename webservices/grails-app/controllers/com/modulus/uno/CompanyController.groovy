package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

@Api
class CompanyController {

  static allowedMethods = [save: "POST", update: "POST"]

  def companyService
  def managerApplicationService

  @ApiOperation(value='Corporate list companies', response = Company, responseContainer = 'list')
  @ApiImplicitParams ([
    @ApiImplicitParam(name = 'corporateId', value = '', dataType = 'number',paramType = 'query')
  ])
  def index() {
    def companies = Corporate.get(params.corporateId) ? Corporate.get(params.corporateId).companies.sort{it.bussinessName} : []
    respond companies, status: 200, formats: ['json']
  }

  @SwaggyShow
  def show() {
    def company = Company.findById(params.id)
    if (company)
      respond company, status: 200, formats: ['json']
    else
      response.sendError(404,"Company not Exist or need send a id" )
  }

  @SwaggySave(extraParams = [
      @ApiImplicitParam(name = 'rfc', value = '', dataType = 'string',paramType = 'form'),
      @ApiImplicitParam(name = 'bussinessName', value = '', dataType = 'string',paramType = 'form'),
      @ApiImplicitParam(name = 'numberOfAuthorizations', value = '', dataType = 'integer',paramType = 'form'),
      @ApiImplicitParam(name = 'grossAnnualBilling', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'employeeNumbers', value = '', dataType = 'integer',paramType = 'form'),
      @ApiImplicitParam(name = 'taxRegime', value = '["MORAL" or "FISICA_EMPRESARIAL"],', dataType = 'String',paramType = 'form'),
      @ApiImplicitParam(name = 'webSite', value = '', dataType = 'string',paramType = 'form'),
      @ApiImplicitParam(name = 'artemisaId', value = '', dataType = 'number',paramType = 'form'),
      @ApiImplicitParam(name = 'corporateId', value = '', dataType = 'number',paramType = 'form')
      ])
  def save(CompanyCommand command) {
    def company = command.createCompany()
    companyService.saveInsideAndAssingCorporate(company,command.corporateId.toLong())
    managerApplicationService.acceptingCompanyToIntegrate(company.id, "")
    respond company, status: 201, formats: ['json']
  }


}
