package com.modulus.uno

import static groovyx.net.http.ContentType.*
import org.apache.http.entity.ContentType.*

class RestService {

  def grailsApplication
  def restClientBean

  def getOnModulus(MessageCommand message, String template) {
    try {
      log.info "Calling Service : ${template}"
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "${template}/${message.uuid}"
      )
      resultGet
    } catch(Exception ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def getInvoiceData(def invoice) {
    try {
      log.info "Calling Service : ${invoice}"
      log.info "provnado el elvio de un archivo"
      restClientBean.uri = "http://api.makingdevs.com"
      restClientBean.post(
        path: "/InvoiceDetail.groovy",
        body:invoice,
        requestContentType: 'application/octet-stream')
    } catch(BusinessException ex){
      log.warm "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def getBalancesIntegrator(String type, String template) {
    try {
      log.info "Calling Service : ${template}"
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "${template}/${type}"
      )
      resultGet
    } catch(BusinessException ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def sendCommand(MessageCommand message, String template){
    try{
      log.info "CALLING Email service: ${template}"
      restClientBean.uri = grailsApplication.config.emailer.url
      restClientBean.post(
        path: template,
        body: message,
        requestContentType: 'application/json' )
    } catch(BusinessException ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def sendCommandWithAuth(MessageCommand message, String template){
    try{
      log.info "CALLING Modulusuno service: ${template}"
      String token = obtainingTokenFromModulusUno()
      def modulusResponse = callingModulusUno(message,template,token)
      log.info "Return Information of modulus uno account to service"
      modulusResponse
    } catch(BusinessException ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }


  def getAuthMap(){
    [
      username:grailsApplication.config.modulus.username,
      password:grailsApplication.config.modulus.password,
      client_id:grailsApplication.config.modulus.clientId,
      client_secret:grailsApplication.config.modulus.secret,
      grant_type:grailsApplication.config.modulus.grant
    ]
  }

  def obtainingTokenFromModulusUno() {
    log.info "Creating RestTemplate Object for obtain token"
    restClientBean.uri = grailsApplication.config.modulus.url
    log.info "Calling Modulus Uno service"
    def tokenResponse = restClientBean.post(
      path: grailsApplication.config.modulus.token,
      body: getAuthMap(),
      requestContentType: URLENC
      )
    log.info "Return token obtained ${tokenResponse.dump()}"
    tokenResponse.responseData.access_token
  }

  private def callingModulusUno(MessageCommand message,String template,String token) {
    log.info "Creating RestTemplate object for create Modulus Uno Account"
    restClientBean.uri = grailsApplication.config.modulus.url
    log.info "Calling service for create account of Modulus Uno"
    def modulusAccountResponse = restClientBean.post(
      path: template,
      headers: [Authorization:"Bearer ${token}"],
      body:message,
      requestContentType: 'application/json')
    log.info "Return of account and properties of Modulus Uno"
    modulusAccountResponse.responseData
  }

  //Metodos de consulta de facturacion
  def sendFacturaCommandWithAuth(MessageCommand message, String template){
    try{
      log.info "CALLING Modulusuno facturacion service: ${template}"
      String token = obtainingFacturaToken()
      def modulusResponse = callingFacturaService(message,template,token)
      log.info "Return Information of modulus uno"
      modulusResponse
    } catch(BusinessException ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  private def obtainingFacturaToken() {
    log.info "Creating RestTemplate Object for obtain token"
    restClientBean.uri = grailsApplication.config.modulus.facturacionUrl
    log.info "Calling Modulus Uno service for token"
    def tokenResponse = restClientBean.post(
      path: grailsApplication.config.modulus.token,
      body: getAuthMap(),
      requestContentType: URLENC
      )
    log.info "Return token obtained ${tokenResponse.dump()}"
    tokenResponse.responseData.access_token
  }

  private def callingFacturaService(MessageCommand message,String template,String token) {
    log.info "Creating RestTemplate object for create Modulus Uno facturacion"
    restClientBean.uri = grailsApplication.config.modulus.facturacionUrl
    log.info "Calling service for creating factura"
    def modulusAccountResponse = restClientBean.post(
      path: template,
      headers: [Authorization:"Bearer ${token}"],
      body:message,
      requestContentType: 'application/json')
    log.info "Returning factura properties of Modulus Uno"
    modulusAccountResponse.responseData
  }

  def getTransactionsAccount(MessageCommand command){
    try {
      log.info "Calling Service : services/integra/tx/getTransactions"
      //String token = obtainingTokenForCreateAccountOfModulusUno()
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "services/integra/tx/getTransactions/${command.uuid}/${command.begin}/${command.end}"
      )
      resultGet.responseData
    } catch(BusinessException ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def getTransactionsIntegrator(MessageCommand command, String template){
    try {
      log.info "Calling Service : ${template}"
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "${template}/${command.type}/${command.begin}/${command.end}"
      )
      resultGet.responseData
    } catch(BusinessException ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def sendFilesForInvoiceM1(def message, def token) {
    try {
      log.info "httpBuilder"
      restClientBean.uri = grailsApplication.config.modulus.documents
      restClientBean.post(
        path: grailsApplication.config.modulus.invoice,
        body:message,
        requestContentType: "multipart/related; boundary=----boundary")
    } catch(BusinessException ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

}
