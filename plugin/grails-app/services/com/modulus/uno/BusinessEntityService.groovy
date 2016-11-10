package com.modulus.uno

import grails.transaction.Transactional

@Transactional
class BusinessEntityService {

  def clientService
  def providerService
  def employeeService

  def appendNamesToBusinessEntity(BusinessEntity businessEntity, String[] names) {
    def name = new ComposeName(value:names[0], type:NameType.NOMBRE)
    def lastName = new ComposeName(value:names[1], type:NameType.APELLIDO_PATERNO)
    def motherLastName = new ComposeName(value:names[2], type:NameType.APELLIDO_MATERNO)
    businessEntity.addToNames(name)
    businessEntity.addToNames(lastName)
    businessEntity.addToNames(motherLastName)
    businessEntity.save()
    businessEntity
  }

  def appendDataToBusinessEntity(BusinessEntity businessEntity, String businessName) {
    businessEntity.addToNames(new ComposeName(value:businessName, type:NameType.RAZON_SOCIAL))
    businessEntity.save()
    businessEntity
  }

  def createAddressForBusinessEntity(Address address, Long businessEntityId) {
    def businessEntity = BusinessEntity.get(businessEntityId)
    businessEntity.addToAddresses(address)
    businessEntity.save()
    businessEntity
  }

  def findBusinessEntityAndProviderLinkByRFC(String rfc){
    def businessEntity =  BusinessEntity.findByRfc(rfc)
    if(businessEntity)
      return ProviderLink.findByProviderRef(businessEntity.rfc)
    businessEntity

  }

  def knowIfBusinessEntityHaveABankAccountOrAddress(def rfc) {
    def businessEntity = BusinessEntity.findByRfc(rfc)
    [businessEntity.banksAccounts,businessEntity.addresses]
  }

  def updateNamesToBusinessEntity(BusinessEntity businessEntity, String[] names) {
    businessEntity.names.each{
      if(it.type == NameType.NOMBRE){
        it.value = names[0]
      }
    }
    businessEntity.names.each{
      if(it.type == NameType.APELLIDO_PATERNO){
        it.value = names[1]
      }
    }
    businessEntity.names.each{
      if(it.type == NameType.APELLIDO_MATERNO){
        it.value = names[2]
      }
    }
    businessEntity.save()
    businessEntity
  }

  def updateDataToBusinessEntity(BusinessEntity businessEntity, String businessName) {
    businessEntity.names.each{
      if(it.type == NameType.RAZON_SOCIAL){
        it.value = businessName
      }
    }
    businessEntity.save()
    businessEntity
  }

  def getClientProviderType(String rfc){
    def providerLink = ProviderLink.findByProviderRef(rfc)
    def clientLink = ClientLink.findByClientRef(rfc)
    def employeeLink = EmployeeLink.findByEmployeeRef(rfc)
    if(providerLink && clientLink) return LeadType.CLIENTE_PROVEEDOR
      if(clientLink) return LeadType.CLIENTE
        if(providerLink) return LeadType.PROVEEDOR
          if (employeeLink) return LeadType.EMPLEADO
  }

  def deleteLinksForRfc(String rfc){
    def clientLink = ClientLink.findByClientRef(rfc)
    clientLink?.delete()
    def providerLink = ProviderLink.findByProviderRef(rfc)
    providerLink?.delete()
  }

  def findBusinessEntityByKeyword(String keyword, String entity, Company company){
    def tokens = keyword ? keyword.tokenize() : ""

    def criteria = BusinessEntity.createCriteria()
    def list = criteria.listDistinct {
      or{
        ilike 'rfc', "%${keyword}%"
        names{
          or {
            'in'("value", tokens)
            ilike "value", "%${keyword}%"
          }
        }
      }
    }

    def leadType = getLeadTypeOfEntity(entity)

    def listOfCompany = company.businessEntities.collect {
      if (getClientProviderType(it.rfc).toString().contains(leadType.toString()) && list.contains(it)){
        it
      }
    }.findResults{it}.unique()

    listOfCompany
  }

  private def getLeadTypeOfEntity(String entity) {
    switch (entity) {
      case "CLIENT": LeadType.CLIENTE
        break
      case "PROVIDER": LeadType.PROVEEDOR
        break
      case "EMPLOYEE": LeadType.EMPLEADO
        break
      default: null
        break
    }
  }

  def generateSubAccountStp(Company company, BusinessEntity businessEntity) {
    ClientLink client = ClientLink.findByCompanyAndClientRef(company, businessEntity.rfc)
    client = clientService.generateSubAccountStp(client, businessEntity)
    client
  }

  ClientLink getClientLinkOfBusinessEntityAndCompany(BusinessEntity businessEntity, Company company) {
    ClientLink.findByCompanyAndClientRef(company, businessEntity.rfc)
  }
}
