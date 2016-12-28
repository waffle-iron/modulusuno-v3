package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.text.SimpleDateFormat
import pl.touk.excel.export.WebXlsxExporter

class CompanyController {

  def springSecurityService
  def companyService
  def userService
  def documentService
  def clientService
  def providerService
  def managerApplicationService
  def modulusUnoService
  RoleService roleService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def user = springSecurityService.currentUser
    def companies = companyService.allCompaniesByUser(user)
    respond companies, model:[companiesCount: companies?.size()]
  }

  def show(Company company) {
    if(!company){
      redirect  (controller: "dashboard" , action:"index")
    } else {
      company.refresh()
      def balance, usd, documents
      if (company.status != CompanyStatus.VALIDATE) {
        params.sepomexUrl = grails.util.Holders.grailsApplication.config.sepomex.url
        if (company.status == CompanyStatus.ACCEPTED) {
          documents = companyService.isAvailableForGenerateInvoces(company.rfc)
          if (company.accounts){
            (balance, usd) = modulusUnoService.consultBalanceOfAccount(company?.accounts?.first()?.timoneUuid)
          }
        }
        def isAvailable = companyService.isEnableToSendNotificationIntegrated(company)
          /*def legalRepresentativesWithDocuments = true
          if (company.taxRegime == CompanyTaxRegime.MORAL)
            legalRepresentativesWithDocuments = userService.containsUsersWithDocumentsByCompany(company.legalRepresentatives,company)*/

        respond company, model:[ clients:clientService.getClientsFromCompany(company),providers:providerService.getProvidersFromCompany(company),available:isAvailable,balance:balance,usd:usd,documents:documents]
      } else {
        flash.message = message(code: 'company.blocket.validate')
        redirect(action:"index")
      }
    }
  }

  def create() {
    respond new Company(params)
  }

  def uploadDocumentsUser() {
    def company = Company.findById(session.company.toLong())
    render view:"/uploadDocuments/uploadDocumentsUser",model:[company:company]
  }

  def save(Company company) {
    if (company == null) {
      notFound()
      return
    }

    if(company.hasErrors()) {
      respond company.errors, view:'create'
      return
    }

    def user = springSecurityService.currentUser
    company.status = CompanyStatus.VALIDATE
    company = companyService.saveInsideAndAssingCorporate(company, user)

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect company
      }
      '*' { respond company, [status: CREATED] }
    }
  }

  def edit(Company company) {
    respond company,model:[edit:true]
  }

  @Transactional
  def update(Company company) {
    if (company == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (company.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond company.errors, view:'edit'
      return
    }

    company.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect company
      }
      '*'{ respond company, [status: OK] }
    }
  }

  def setCompanyInSession() {
    def company = params.company
    session['company'] = company
    def currentUser = springSecurityService.currentUser
    def companyInfo = Company.findById(company.toLong())
    roleService.updateTheUserRolesOfUserAtThisCompany(currentUser,companyInfo)
    if (companyInfo.status != CompanyStatus.ACCEPTED) {
      redirect(action:"show",id:"${company}")
      return
    }
    redirect(action:"accountStatement")
  }

  def rejected(Company company) {
    def rowsOfReasons = managerApplicationService.obtainReasonOfRejectedCompanyRequestByStatus(company,true)
    def rowDocuments = rowsOfReasons.findAll {row -> row.typeClass == "document"}
    def rowRepresentatives = rowsOfReasons.findAll {row -> row.typeClass == "legalRepresentative"}
    def rowCompany = rowsOfReasons.find {row -> row.typeClass == "company"}
    def documentsByUser = managerApplicationService.getMapOfUsersWithDocuments(company.legalRepresentatives,company.id)
    [company:company,legalRepresentatives:documentsByUser,reasonDocuments:rowDocuments,reasonRepresentatives:rowRepresentatives,reasonCompany:rowCompany, baseUrlDocuments:grailsApplication.config.grails.url.base.images]
  }

  @Transactional
  def delete(Company company) {

    if (company == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    company.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }

  def accountStatement(){
    def roles = springSecurityService.getPrincipal().getAuthorities()
    def company
    if (Role.findByAuthority(roles.first()) == Role.findByAuthority("ROLE_ADMIN")) {
      company = Company.get(params.company)
    } else {
      company = Company.get(session.company.toLong())
    }

    String startDate = params.startDate ? new SimpleDateFormat("dd-MM-yyyy").format(params.startDate) : ""
    String endDate = params.endDate ? new SimpleDateFormat("dd-MM-yyyy").format(params.endDate) : ""
    AccountStatement accountStatement = companyService.getAccountStatementOfCompany(company, startDate, endDate)

    respond accountStatement
  }

  def generatePdfForAccountStatement() {
    Company company = Company.get(params.company)
    String startDate = params.startDate ? new SimpleDateFormat("dd-MM-yyyy").format(new Date(params.startDate)) : ""
    String endDate = params.endDate ? new SimpleDateFormat("dd-MM-yyyy").format(new Date(params.endDate)) : ""
    AccountStatement accountStatement = companyService.getAccountStatementOfCompany(company, startDate, endDate)
    renderPdf(template: "/documentTemplates/accountStatementCompany", model: [accountStatement:accountStatement, startDateFormatted:startDate, endDateFormatted:endDate])
  }

  def generateXlsForAccountStatement() {
    Company company = Company.get(params.company)
    String startDate = params.startDate ? new SimpleDateFormat("dd-MM-yyyy").format(new Date(params.startDate)) : ""
    String endDate = params.endDate ? new SimpleDateFormat("dd-MM-yyyy").format(new Date(params.endDate)) : ""
    AccountStatement accountStatement = companyService.getAccountStatementOfCompany(company, startDate, endDate)

    def headers = ['Fecha', 'Tipo', 'Id. Transacción', 'Abono', 'Cargo', 'Saldo']
    def withProperties = ['date', 'type', 'id', 'credit', 'debit', 'balance']
    def formattedTransactions = companyService.formattingTransactionsForXls(accountStatement.transactions)
    println "Transacciones: ${formattedTransactions}"
    new WebXlsxExporter().with {
      setResponseHeaders(response)
      fillRow(["Estado de Cuenta - ${accountStatement.company.bussinessName}"], 0)
      fillRow(["Desde el: ${startDate}, Hasta el: ${endDate}"], 2)
      fillRow(headers, 4)
      add(formattedTransactions, withProperties, 5)
      save(response.outputStream)
    }

  }
  def sendFilesToCreateInvoice() {
    def company = Company.findById(session.company.toLong())
    def responseStatus = companyService.sendDocumentsPerInvoice(params, company.rfc)
    flash.responseStatus = responseStatus
    redirect(action:"show",id:"${session.company.toLong()}")
  }

}
