package com.modulus.uno

class CompanyTagLib {

  def springSecurityService

  static namespace = "modulusuno"
  static defaultEncodeAs = [taglib:'text']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def statusForCompany = { attrs, body ->
    def labelStatus = [
      (CompanyStatus.CREATED) : "label-primary",
      (CompanyStatus.VALIDATE): "label-warning",
      (CompanyStatus.ACCEPTED): "label-success",
      (CompanyStatus.REJECTED): "label-danger" ]
    out << """
      <span class="label ${labelStatus[attrs.status] ?: 'label-default'}">
        ${g.message(code:attrs.status.code)}
      </span>
    """
  }

  def userLoggin = { attrs, body ->
    out << "${springSecurityService.currentUser.username}"
  }

}
