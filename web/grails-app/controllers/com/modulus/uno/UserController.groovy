package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

  def userService
  def companyService
  def legalRepresentativeService

  static defaultAction = "create"
  static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond User.list(params), model:[userCount: User.count()]
  }

  def show(User user) {
    respond user
  }

  def profile(User user) {
    render view:"profile", model:[user:user]
  }

  def create() {
    render view:"create", model:[user:new UserCommand(),legal:params.legal,company:session.company]
  }

  def legalRepresentative() {
    [user:new UserCommand(),legal:params.legal,company:session.company]
  }

  def authorizer() {
    [user:new UserCommand(),authorize:true,company:session.company, authorities:Role.findAllByAuthorityInList(['ROLE_INTEGRADO_OPERADOR', 'ROLE_INTEGRADO_AUTORIZADOR'])]
  }

  @Transactional
  def save(UserCommand command) {
    command.legal = params.legal
    if (command.hasErrors()) {
      if (params.authorize) {
        render(view:'authorizer', model:[user:command, authorize:true, roleId:params.roleId, company:session.company, authorities:Role.findAllByAuthorityInList(['ROLE_INTEGRADO_OPERADOR', 'ROLE_INTEGRADO_AUTORIZADOR'])])
      } else {
        render(view:params.legal?'legalRepresentative':'create', model:[user:command, legal:params.legal])
      }
      return
    }

    Company company = Company.get(session.company)

    User user = userService.createUser(command)
    user = userService.setRoleToUser(user,params,company)

    flash.message = g.message(code: 'login.create')
    if(params.legal || params.authorize) {
      render view:"show", model:[user:user,company:session.company]
      return
    }

    render view:"detail", model:[user:user,company:session.company]
  }

  def edit(User user) {
    respond user,model:[company:session.company]
  }

  @Transactional
  def update() {
    def user = User.get(params.user)
    Company company = Company.get(session.company)
    legalRepresentativeService.assignLegalRepresentativeToCompany(company.id,user.id)
    userService.addInformationToLegalRepresentative(user,params)
    render view:"show", model:[user:user,company:session.company]
  }

  def delete(User user) {

    if (user == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    user.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  def manageusers() {
    Company company = Company.get(session.company)
    [users: company.actors]
  }

  @Transactional
  def updateauthorities(){
    Company company = Company.get(session.company)
    userService.updateAuthoritiesUsers(company, params)
    flash.message = "Usuarios actualizados"
    redirect (action:'manageusers')
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
