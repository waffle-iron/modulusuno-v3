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
              <asset:image src="Logo-ModulusUno-vFINAL-big.png" class="shadow" width="40%"/>
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
              <H3>Envio de solictud exitoso</H3>
              <br/>
              Se revisara la solicitud de su empresa y se crearan las cuentas necesarias para su operacion.
              <br/><br/>
              <g:link action="logout" controller="integradora" class="btn btn-success">Aceptar</g:link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
