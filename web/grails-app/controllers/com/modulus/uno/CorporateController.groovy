package com.modulus.uno

class CorporateController {

  CorporateService corporateService
  def springSecurityService

  def create(){
    respond new Corporate()
  }

  def save(Corporate corporate){
    if(!corporate){
      transactionStatus.setRollbackOnly()
      respond corporate.errors, view:'create'
      return
    }

    corporateService.saveNewCorporate(corporate)

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

  def addUser(Corporate corporate){
    if(!corporate)
      return response.sendError(404)

    render view:"newUser",model:[user:new UserCommand(),
                                 corporateId:corporate.id]
  }

  def saveUser(UserCommand userCommand){
    Long corporateId = params.long("corporate")
    if(userCommand.hasErrors()){
      render(view:"/corporate/newUser",model:[user:userCommand,
                                              corporateId:corporateId])
      return
    }

    User user = new User(username:userCommand.username,password:userCommand.password)
    Profile profile = userCommand.getProfile()
    Telephone telephone = userCommand.getTelephone()

    if(telephone)
      profile.addToTelephones(telephone)

    user.profile = profile

    corporateService.addUserToCorporate(corporateId,user)

    redirect(action:"show",id:corporateId)
  }

  def assignRolesInCompaniesForUser(User user){
    Corporate corporate = corporateService.findCorporateOfUser(user)
    [companies:corporate.companies,roles:Role.list()]
  }

}
