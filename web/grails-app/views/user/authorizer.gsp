<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
        <i class="fa fa-user-plus fa-3x"></i>
        Administración / Alta Usuario
        <small>Agregar nuevos usuarios en  la compañia.</small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Nuevo Usuario</li>
      </ol>
    </div>
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
          <ul class="errors alert alert-danger alert-dismissable" role="alert">
            <g:eachError bean="${this.user}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
              </g:eachError>
          </ul>
            </g:hasErrors>
            <g:form action="save">
            <fieldset>
              <input type="hidden" value="${company}" name="company" />
              <input type="hidden" value="${authorize}" name="authorize" />
              <g:render template="form" bean="${user}" />
            </fieldset>
            <div class="text-right">
              <g:submitButton name="create" class="btn btn-lg btn-default" value="${message(code: 'user.button.create.label', default: 'Create')}" />
            </div>

              </g:form>
        </div>
      </div>
    </div>
    <g:link controller="company" action="show" id="${session.company}" class="btn btn-primary">
    <g:message code="company.show"/>
    </g:link>
  </body>
</html>
