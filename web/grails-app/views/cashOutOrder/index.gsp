<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cashOutOrder.label', default: 'CashOutOrder')}" />
        <title><g:message code="cashoutOrder.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="cashoutOrder.list.label" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i>Compañia</li>
          <li class="active">Listado de tus ordenes de retiro</li>
        </ol>
      </div>
        <div id="list-cashOutOrder" class="content portlet-blue" role="main">
            <g:if test="${flash.message}">
                <div class="message alert alert-info" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.error}">
                <div class="message alert alert-danger" role="status">${flash.error}</div>
            </g:if>
            <div class="table-responsive">
              <table class="table">
                <tr>
                  <th>Monto de Retiro</th>
                  <th>Cuenta Destino</th>
                  <th>Empresa</th>
                  <th>Estatus</th>
                  <th>Cuenta Origen</th>
                </tr>
                <g:each in="${cashOutOrderList}" var="cashoutOrder">
                <tr>
                  <td>
                    <g:link action="show" id="${cashoutOrder.id}" params="[company:"${cashoutOrder.company.id}"]">
                    ${modulusuno.formatPrice(number: cashoutOrder.amount)}
                  </g:link>
                  </td>
                  <td>
                    <g:link controller="bankAccount" action="show" id="${cashoutOrder.account.id}">
                    ${cashoutOrder.account}
                    </g:link>
                  </td>
                  <td>
                    <g:link controller="company" action="show" id="${cashoutOrder.company.id}">
                    ${cashoutOrder.company}
                    </g:link>
                  </td>
                  <td>${cashoutOrder.status}</td>
                  <td>${cashoutOrder.timoneAccount}</td>
                </tr>
                </g:each>
                </table>
            </div>
            <div class="pagination">
                <g:paginate total="${cashOutOrderCount ?: 0}" />
            </div>
        </div>
        <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
          <g:link class="create btn btn-default" action="create"><g:message code="cashoutOrder.new.label" args="[entityName]" /></g:link>
        </sec:ifAnyGranted>
    </body>
</html>
