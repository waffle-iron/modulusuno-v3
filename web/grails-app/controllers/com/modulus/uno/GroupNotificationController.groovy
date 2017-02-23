package com.modulus.uno

class GroupNotificationController {

    def emailerClientService
    def groupNotificationService
    def corporateService

    def create() {
      def emailersStorage = emailerClientService.getEmailerStorage()
      render (view:"create", model: [emailers : emailersStorage, users:corporateService.findCorporateUsers(session.corporate.id)])
    }

    def save(){
        groupNotificationService.addNewGroup(params, session.corporate.id)
        render (view:"create")
    }

}
