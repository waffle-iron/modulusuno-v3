<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="user.edit"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Informacion del Representante legal</li>
      </ol>
    </div>
    <p>
    <g:link controller="company" action="show" id="${company}" class="btn btn-default">
      <g:message code="company.show"/>
    </g:link>
    </p>
    <div class="container">
      <div class="row">
          <div class="portlet portlet-blue">
            <div class="portlet-heading login-heading">
              <div class="portlet-title">
                <br />
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                  <g:eachError bean="${this.user}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
                </ul>
              </g:hasErrors>
              <g:form action="update" class="form">
                <fieldset class="form">
                  <input type="hidden" name="company" value="${company}" />
                  <input type="hidden" name="user" value="${user.id}" />
                  <f:with bean="user">
                    <f:field property="profile.name" label="${message(code:'user.name')}" wrapper="home"/>
                    <f:field property="profile.lastName" label="${message(code:'user.lastName')}" wrapper="home"/>
                    <f:field property="profile.motherLastName" label="${message(code:'user.motherLastName')}"wrapper="home"/>
                    <f:field property="profile.email" label="${message(code:'user.email')}" wrapper="home"/>
                    <f:field property="profile.rfc" label="${message(code:'user.rfc')}" required="true" wrapper="home"/>
                    <f:field property="profile.curp" label="${message(code:'user.curp')}" required="true" wrapper="home"/>
                    <div class="form-group">
                      <label><g:message code="user.birthdate" /></label>
                      <input type="date" value="${new Date()}" name="profile.birthdate" class="form-control"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label"><g:message code="user.gender" /><span class="required-indicator">*</span></label>
                      <g:select name="banco" from="${com.modulus.uno.Gender.values()}" class="form-control" aria-controls="example-table"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label"><g:message code="user.nationality" /><span class="required-indicator">*</span></label>
                      <g:select name="banco" from="${com.modulus.uno.Nationality.values()}" class="form-control" aria-controls="example-table"/>
                    </div>

                                </f:with>
                </fieldset>
                <fieldset class="buttons">
                  <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
              </g:form>
            </div>
          </div>
      </div>
    </div>
  </body>
</html>
