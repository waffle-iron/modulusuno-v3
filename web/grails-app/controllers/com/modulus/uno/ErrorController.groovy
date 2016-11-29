package com.modulus.uno

import com.ullink.slack.simpleslackapi.SlackSession
import grails.util.Environment

class ErrorController {

  def springSecurityService
  SlackSession slackSession

  def serverError() {
    try{
      def exception = request.exception
      if(grailsApplication.config.slack.active){
        def channelName = grailsApplication.config.slack.channelName
        def channel = slackSession.channels.find { c -> c.name == channelName  }
        slackSession.sendMessage channel,  """\
          Hey @here !!!
          An error throwed by user: ${springSecurityService?.currentUser?.username ?: 'No user identified(Maybe an API call)'}
          exception ${exception?.className},
          line ${exception?.lineNumber}
          has throw ${exception?.cause}
          Environment: ${Environment.current}
          Source: ${request.getHeader('referer')}
          Domain: ${request.getHeader('host')}
        """
      }

      log.error "Exception: ${exception?.className}, Line: ${exception?.lineNumber}, Throw: ${exception?.cause}"

      render view:"serverError", model:[exception:exception]
    } catch(e){
      render "No se puede manejar el error ${e}"
    }
  }
}
