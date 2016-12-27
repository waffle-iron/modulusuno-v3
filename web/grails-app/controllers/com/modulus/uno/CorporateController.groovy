package com.modulus.uno

class CorporateController {

  CorporateService corporateService
  UserService userService
  OrganizationService organizationService
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

  def assignRolesInCompaniesForUser(User user){
    Corporate corporate = corporateService.findCorporateOfUser(user)
    List<Role> roles = Role.list()
    roles = roles.findAll{ it.toString() in ["ROLE_LEGAL_REPRESENTATIVE_VISOR",
                          "ROLE_LEGAL_REPRESENTATIVE_EJECUTOR",
                          "ROLE_FICO_VISOR",
                          "ROLE_FICO_EJECUTOR",
                          "ROLE_AUTHORIZER_VISOR",
                          "ROLE_AUTHORIZER_EJECUTOR",
                          "ROLE_OPERATOR_VISOR",
                          "ROLE_OPERATOR_EJECUTOR"]}
    [companies:corporate.companies,roles:roles,user:user]
  }

  def saveRolesForUser(RolesCompanyCommand command){
    organizationService.createRolesForUserInCompanies(command.username,command.rolesByCompany())
    redirect(action:"assignRolesInCompaniesForUser",id:5)
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
    corporateService.addNewUserToCorporate(corporateId,user)

    if(roles[0].authority == "ROLE_M1"){
      userService.setAuthorityToUser(user,'ROLE_CORPORATIVE')
      redirect(action:"show",id:corporateId)
      return
    }
    else{
      redirect(action:"users",id:corporateId)
      return
    }
  }

  def users(Corporate corporate){
    if(!corporate)
      return response.sendError(404)

    [users:corporateService.findCorporateUsers(corporate.id)]
  }

}

@groovy.transform.TypeChecked
class RolesCompanyCommand {
  String username
  Map<String, Map<String, Boolean>> companies

  Map filterDataInCommand(){
    companies.findAll { k,v -> !k.contains(".") }
  }

  Map rolesByCompany(){
    def rolesByCompany = [:]
    def fullData = filterDataInCommand()
    fullData.each { company, roles ->
      rolesByCompany["$company"] = roles.findAll { String k,v -> !k.startsWith("_") }
    }
    rolesByCompany
  }
}
