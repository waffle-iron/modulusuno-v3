package com.modulus.uno

import com.github.rahulsom.swaggydoc.*
import com.wordnik.swagger.annotations.*
import static org.springframework.http.HttpStatus.*
import grails.converters.JSON

@Api
class STPController {

  static allowedMethods = [stpDepositNotification:"POST"]

  def stpDepositService

  @SwaggySave(extraParams = [
    @ApiImplicitParam(name = 'notification', value = '', dataType = 'string',paramType = 'query')
  ])
  def stpDepositNotification() {
    try {
      log.info "Receiving notificaction: ${params.notification}"
      def result = stpDepositService.notificationDepositFromStp(params.notification)
      respond result, status: 201, formats: ['json']
    } catch (Exception ex) {
      response.sendError(422, "Missing parameters from notification, error: ${ex.message}")
    }
  }

}
