package com.modulus.uno

class CompanySelectTagLib {

  def companyService
  def springSecurityService
  def restService

  static namespace = "companyInfo"
  static defaultEncodeAs = "raw"

  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def companyInfo = { attrs, body ->
    def company = Company.findById(session.company.toLong())
    out << "${company.toString()}"
  }

  def selectedCompany = { attrs,body ->
    def user = springSecurityService.currentUser
    def companies = companyService.findCompaniesForThisUser(user)
    out << g.select(from:companies, id:"companyNavSelect", name:"company",optionKey:"id", value:"${session.company}",required:"required")
  }

  def isAvailableForOperationInThisCompany = { attrs, body ->
    def company = Company.findById(session.company.toLong())
    out << (company.status == CompanyStatus.ACCEPTED)
  }

  def listTemplatesPdfForCompany = { attrs, body ->
    def emisor = restService.existEmisorForGenerateInvoice(attrs.rfc)
    if (emisor.templatesPdf.size()>1) {
      out << g.select(from:emisor.templatesPdf, name:"pdfTemplate", required:"required")
    }
  }

}
