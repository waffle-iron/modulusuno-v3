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
          Origin: ${request.getHeader('referer')}
          Destiny: ${request.request.request.request.strippedServletPath}
          Parameters: ${request.parameterMap.toString()}
        """
      }

      log.error "User: ${springSecurityService?.currentUser?.username ?: 'No user identified(Maybe an API call)'}, Exception: ${exception?.className}, Line: ${exception?.lineNumber}, Throw: ${exception?.cause}, Origin: ${request.getHeader('referer')}, Destiny: ${request.request.request.request.strippedServletPath}, Parameters: ${request.parameterMap.toString()}"

      render view:"serverError", model:[exception:exception, originUrl:request.getHeader('referer'), destinyUrl:request.request.request.request.strippedServletPath]
    } catch(e){
      render "No se puede manejar el error ${e}"
    }
  }
}
