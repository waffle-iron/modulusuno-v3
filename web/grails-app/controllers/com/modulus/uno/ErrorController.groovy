package com.modulus.uno

import com.ullink.slack.simpleslackapi.SlackSession

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
          Source: ${request.getRemoteHost()}
          Domain: ${new URL(request.getRequestURL().toString()).getHost()}
        """
      }
      render view:'serverError', model:[exception:exception]
    } catch(e){
      render "No se puede manejar el error ${e}"
    }
  }
}
