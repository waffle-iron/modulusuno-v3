package com.modulus.uno
import grails.transaction.Transactional


@Transactional(readOnly = true)
class CorporateController {

  CorporateService corporateService
  def springSecurityService

  def create(){
    respond new Corporate(params)
  }

  @Transactional
  def save(Corporate corporate){
    if(!corporate){
      transactionStatus.setRollbackOnly()
      respond corporate.errors, view:'create'
      return
    }

    def user = springSecurityService.currentUser
    corporateService.saveNewCorporate(corporate,user)

    request.withFormat {
      form multipartForm {
        flash.message = message(code:'default.created.message', args:[message(code: 'corporate.label',
                                                                              default: 'Corporate'),corporate.id])
        redirect corporate
      }
      '*'{ respond corporate, [status:CREATED] }
    }
  }

  def show(Corporate corporate){
    if(!corporate)
      return response.sendError(404)

    respond corporate,model:[]
  }

}
