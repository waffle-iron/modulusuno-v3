<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios')}" />
    </head>
    <body>
        <div id="create-movimientosBancarios" class="content scaffold-create" role="main">
          <h1>Crear Movimiento Bancario para la cuenta ${movimientosBancarios.cuenta}</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.movimientosBancarios}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.movimientosBancarios}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
              <input type="hidden" name="cuenta" value="${movimientosBancarios.cuenta.id}" />
                <fieldset class="form">
                 <g:render template="form"  />
                </fieldset>
                    <g:submitButton name="create" class="btn  btn-green" value="${message(code: 'default.button.create.label', default: 'Create')}" />
            </g:form>
        </div>
        <asset:javascript src="movimientosBancarios/create.js"/>
    </body>
</html>
