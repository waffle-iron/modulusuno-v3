<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>Órdenes de Pago a Proveedores para ${company}</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> ${company}</li>
        <li class="active">Listado de órdenes</li>
      </ol>
    </div>

    <g:if test="${flash.message}">
    <div class="bg-primary">${flash.message}</div>
    </g:if>

    <g:link class="create" action="create" class="btn btn-default" params="[companyId:company.id]">
      Nueva orden de compra
    </g:link>
    <br>
    <br>

    <table class="table table-condensed table-striped">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>Cliente</th>
          <th>Fecha de creación</th>
          <th>Estado</th>
        </tr>
      </thead>
      <tbody>
        <g:each in="${purchaseOrderList}" var="purchaseOrder">
          <tr>
            <td><g:link action="show" id="${purchaseOrder.id}" class="btn btn-default btn-xs"><i class="fa fa-eye"></i> Ver</g:link></td>
            <td>
              <g:link action="show" id="${purchaseOrder.id}">${purchaseOrder.providerName}</g:link>
            </td>
            <td>${formatDate(date:purchaseOrder.dateCreated, format:'dd-MMMM-yyyy HH:mm')}</td>
            <td>${purchaseOrder.status}</td>
          </tr>
        </g:each>
      </tbody>
    </table>

    <div class="pagination">
      <g:paginate total="${purchaseOrderCount ?: 0}" />
    </div>
  </body>
</html>
