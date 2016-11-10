<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="home" />
    <asset:stylesheet src="third-party/modulus-uno-theme/css/style.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/plugins.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/demo.css"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>

    <div class="container">
      <div class="row">
        <div class="col-md-6 col-md-offset-3">
          <div class="login-banner text-center">
            <h1>
              <asset:image src="logo-iecce-blank.png" class="shadow" />
            </h1>
          </div>
          <div class="portlet portlet-green">
            <div class="portlet-heading login-heading">
              <div class="portlet-title">
                <h4><g:message code="user.view.show.label" args="[entityName]" /></h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
              </g:if>
              <H3>Proceso de integración activado</H3>
              <br/>
              Su empresa está en proceso de ser integrada, por favor espere a completar la validación de sus datos.
              <br/><br/>
              <g:link action="logout" controller="integradora" class="btn btn-success">Aceptar</g:link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
