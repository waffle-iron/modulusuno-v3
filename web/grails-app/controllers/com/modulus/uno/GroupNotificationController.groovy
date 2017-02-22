package com.modulus.uno

class GroupNotificationController {

    def emailerClientService

    def create() {
      def emailersStorage = emailerClientService.getEmailerStorage()
      //Regresar la lista de usuarios
      render (view:"create", model: [emailers : emailersStorage])
    }

}
