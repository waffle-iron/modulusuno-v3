package com.modulus.uno

import grails.converters.JSON
import static org.springframework.http.HttpStatus.*

class ExceptionController {

  def index() { }

  def handleError() {
    Exception exception = request.exception
    def error = exception?.cause?.target
    response.status = 400
    withFormat {
      json {
        render(contentType: "text/json") { response ERROR: [code: 400, msg: "${error}"] }
      }
    }
  }
}
