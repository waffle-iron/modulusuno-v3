package com.modulus.uno

class CorporateController {

  CorporateService corporateService
  UserService userService
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

    ArrayList<Role> roles = springSecurityService.getPrincipal().getAuthorities()
    ArrayList<User> users = []

    respond corporate,model:[]
  }

  def createUser(){
    User user = springSecurityService.currentUser
    Corporate corporate = corporateService.findCorporateOfUser(user)
    if(!corporate)
      return response.sendError(404)

    redirect(action:"addUser",id:corporate.id)
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

    Profile profile = userCommand.getProfile()
    Telephone telephone = userCommand.getTelephone()

    if(telephone)
      profile.addToTelephones(telephone)

    User user =  userService.createUserWithoutRole(new User(username:userCommand.username,
                                                            password:userCommand.password),profile)

    ArrayList<Role> roles = springSecurityService.getPrincipal().getAuthorities()

    if(roles[0].authority == "ROLE_M1"){
      corporateService.addNewUserToCorporate(corporateId,user,"ROLE_CORPORATIVE")
      redirect(action:"show",id:corporateId)
      return
    }
    else{
      corporateService.addNewUserToCorporate(corporateId,user)
      redirect(action:"show",id:corporateId)
      return
    }
  }


  def assignRolesInCompaniesForUser(User user){
    Corporate corporate = corporateService.findCorporateOfUser(user)
    [companies:corporate.companies,roles:Role.list()]
  }

}
