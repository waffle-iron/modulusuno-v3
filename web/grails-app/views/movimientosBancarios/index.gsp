<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-movimientosBancarios" class="content scaffold-list" role="main">
            <h1>Listado de Cuentas Bancarias </h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <p>
              <center>
               <b>La Fecha del día de hoy es: ${new Date().format("dd/MMMM/yyy")}</b>
              </center>
            </p>
            <g:each in="${bankAccountsOfCompany}" var="account">
              ${account}&nbsp;
              <g:link action="show" id="${account.id}" class="btn btn-green" ><i class="fa fa-search" aria-hidden="true"></i></g:link>&nbsp;
              <g:link controller="movimientosBancarios" action="create" class="btn btn-green" id="${account.id}"><i class="fa fa-plus" aria-hidden="true"></i></g:link> &nbsp;
               Saldo Total: <modulusuno:amountAccountToday id="${account.id}">
            </g:each>

            <div class="pagination">
            </div>
        </div>
    </body>
</html>
