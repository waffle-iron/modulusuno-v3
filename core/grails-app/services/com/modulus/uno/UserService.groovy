package com.modulus.uno

class UserService {

  def recoveryService
  def documentService
  def companyService

  def addInformationToLegalRepresentative(def user, def params) {
    def profile = user.profile
    profile.name = params.profile.name
    profile.lastName = params.profile.lastName
    profile.motherLastName = params.profile.motherLastName
    profile.email = params.profile.email
    profile.rfc = params.profile.rfc
    profile.curp = params.profile.curp
    profile.trademark = params.profile.trademark
    profile.fullName = params.profile.fullName
    profile.birthDate = params.profile.birthDate
    if (params.telephone?.id) {
      updateTelephoneOfUser(params)
    } else {
      profile.addToTelephones(createTelephone(params))
    }
    profile.save()
  }

  def containsUsersWithDocumentsByCompany(def listOfUsers,Company company) {
    def mapUserWithDocs = []
    listOfUsers.each{ user ->
      def listOfDocuments = documentService.getDocumentsByCompanyForLegalRepresentative(user.profile.documents,company.id)
      def documentsAvilable = false
      if (listOfDocuments.size() == 4)
        documentsAvilable = true
      mapUserWithDocs.add(documentsAvilable)
    }
    if (mapUserWithDocs.contains(false)) {
      return false
    } else {
      return true
    }
  }

  def createRelationshipIntoLegalRepresentativeAndAsset(S3Asset asset, def userId) {
    def user = User.findById(userId)
    user.profile.addtoDocuments = asset
    user.save()
    user
  }

  User createUser(UserCommand command){
    User user = new User(username: command.username, password:command.password)
    def profile = command.getProfile()
    def telephone = command.getTelephone()
    if (telephone) {
      profile.addToTelephones(telephone)
    }
    user.profile = profile
    user
  }

  User setRoleToUser(User user, def params,Company company) {
    def userRole = Role.findWhere(authority: 'ROLE_INTEGRADO')
    if(params.legal) {
      userRole = Role.findWhere(authority: 'ROLE_LEGAL_REPRESENTATIVE')
      companyService.addingLegalRepresentativeToCompany(company, user)
    } else if (params.authorize) {
      userRole = Role.get(params.roleId)
      companyService.addingActorToCompany(company, user)
    }
    save(user, userRole)
    user
  }

  def getUsersByRole(String srol){
    def users = User.list().findAll { user ->
      if (user.authorities.any { it.authority == srol })
        return user
    }
    users
  }

  def save(User user, Role role){
    user.save()
    UserRole.create user, role, true
    recoveryService.sendConfirmationAccountToken(user.profile?.email)
  }

  def updateAuthoritiesUsers(Company company, params){
    Role roleop = Role.findByAuthority("ROLE_INTEGRADO_OPERADOR")
    Role roleau = Role.findByAuthority("ROLE_INTEGRADO_AUTORIZADOR")
    company.actors.each(){
      User us = it
      String oper = "operator"+us.id
      String auth = "authorizer"+us.id
      if (params.get(oper) && !us.authorities.find{it.authority.equals("ROLE_INTEGRADO_OPERADOR")})
        UserRole.create us, roleop, true
      else if (!params.get(oper) && us.authorities.find{it.authority.equals("ROLE_INTEGRADO_OPERADOR")})
        UserRole.remove us, roleop, true

      if (params.get(auth) && !us.authorities.find{it.authority.equals("ROLE_INTEGRADO_AUTORIZADOR")})
        UserRole.create us, roleau, true
      else if (!params.get(auth) && us.authorities.find{it.authority.equals("ROLE_INTEGRADO_AUTORIZADOR")})
        UserRole.remove us, roleau, true
    }
  }

  private def createTelephone(params) {
    def telephone = new Telephone()
    telephone.number = params.number
    telephone.extension = params.extension
    telephone.type = params.type
    telephone.save(failOnError:true)
    telephone
  }

  private def updateTelephoneOfUser(params) {
    def telephone = Telephone.findById(params.telephone.id)
    telephone.number = params.telephone.number
    telephone.extension = params.telephone.extension
    telephone.type = params.telephone.type
    telephone.save()
  }

}
