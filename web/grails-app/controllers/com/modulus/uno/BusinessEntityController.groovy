package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BusinessEntityController {

  def businessEntityService
  def clientService
  def providerService
  def restService
  def springSecurityService
  def employeeService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", createAccountByProvider: "POST"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def roles = springSecurityService.getPrincipal().getAuthorities()
    def company
    if (Role.findByAuthority(roles.first()) == Role.findByAuthority("ROLE_ADMIN")) {
      company = Company.findById(params.company)
    } else {
      company = Company.findById(session.company.toLong())
    }
    max = Math.min(max ?: 10, 100)
    def offset = params.offset? params.offset.toInteger() : 0
    def total = company.businessEntities.size()
    def allBusinessEntitiesCompany = company.businessEntities.toList().sort{it.id}
    def businessEntityList = allBusinessEntitiesCompany.subList(Math.min(offset, total), Math.min(offset+max,total))

    respond businessEntityList, model:[businessEntityList:businessEntityList, businessEntityCount: total]
  }

  def show(BusinessEntity businessEntity) {
    params.sepomexUrl = grails.util.Holders.grailsApplication.config.sepomex.url
    LeadType relation = businessEntityService.getClientProviderType(businessEntity.rfc)
    respond businessEntity, model:[relation:relation.toString(),clientLink: businessEntityService.getClientLinkOfBusinessEntityAndCompany(businessEntity, Company.get(session.company))]
  }

  def create() {

    def businessEntity = new BusinessEntity(params)
    respond businessEntity, model:[clientProviderType: params.clientProviderType]
  }

  def createAccountByProvider() {
    def businessEntity = BusinessEntity.findByRfc(params.rfcBank)
    redirect action:"show",id:businessEntity.id
  }

  @Transactional
 def save(BusinessEntityCommand command) {

   command.clientProviderType = params.clientProviderType
   if (params.clientProviderType.equals("EMPLEADO")){
     command.website="http://www.employee.com"
     command.type = BusinessEntityType.FISICA
     params.persona = 'fisica'
   }

   BusinessEntity businessEntity = new BusinessEntity(command.properties)
   if (command.hasErrors()) {
     render(view:'create', model:[command:command, businessEntity:businessEntity, clientProviderType:params.clientProviderType], params:params,banks:Bank.list().sort{ it.name })
     return
   }

   Company company = Company.findById(session.company.toLong())
   businessEntityService.generatedBussinessEntityProperties(businessEntity, params, company)

   request.withFormat {
     form multipartForm {
       flash.message = message(code: 'businessEntity.created', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
       redirect action: 'show', id:businessEntity.id
     }
     '*' { respond businessEntity, [status: CREATED] }
   }
 }

  def edit(BusinessEntity businessEntity) {
    String clientProviderType = businessEntityService.getClientProviderType(businessEntity.rfc)
    respond businessEntity, model:[clientProviderType:clientProviderType]
  }

  @Transactional
  def update(BusinessEntity businessEntity) {
    if (businessEntity == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (businessEntity.hasErrors()) {
      transactionStatus.setRollbackOnly()
      params.edit = true
      String clientProviderType = businessEntityService.getClientProviderType(businessEntity.rfc)
      render  view:'edit', model:[businessEntity:businessEntity, clientProviderType:clientProviderType, params:params]
      return
    }

    def company = Company.findById(session.company.toLong())
    LeadType leadType = LeadType."${params.clientProviderType}"
    if(businessEntity.type == BusinessEntityType.FISICA){
      businessEntityService.updateNamesToBusinessEntity(businessEntity, (String[])[params.name, params.lastName, params.motherLastName])
    } else {
      businessEntityService.updateDataToBusinessEntity(businessEntity, params.businessName)
    }
    businessEntityService.deleteLinksForRfc(businessEntity.rfc)
    if(leadType == LeadType.CLIENTE || leadType == LeadType.CLIENTE_PROVEEDOR){
      clientService.addClientToCompany(businessEntity, company)
    }
    if(leadType == LeadType.PROVEEDOR || leadType == LeadType.CLIENTE_PROVEEDOR){
      providerService.addProviderToCompany(businessEntity, company)
    }

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'businessEntity.updated', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
        redirect action: 'show', id:businessEntity.id
      }
      '*'{ respond businessEntity, [status: OK] }
    }
  }

  @Transactional
  def delete(BusinessEntity businessEntity) {

    if (businessEntity == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    businessEntity.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  def search(){
    def company = Company.get(params.long("company"))
    def businessEntityList = businessEntityService.findBusinessEntityByKeyword(params.rfc, null, company)
    if(businessEntityList.isEmpty()){
      flash.message = "No se encontr\u00F3 cliente o proveedor."
    }

    render view:'index',model:[companyId:company.id, businessEntityList:businessEntityList]
  }

  @Transactional
  def generateSubAccountStp(BusinessEntity businessEntity) {
    Company company = Company.get(session.company)
    businessEntityService.generateSubAccountStp(company, businessEntity)
    redirect action:"show", id:businessEntity.id
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
