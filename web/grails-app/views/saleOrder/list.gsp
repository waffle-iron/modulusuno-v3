<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'DepositOrder')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <div class="page-title">
    <h1>
      <i class="icon-factura fa-3x"></i>
      <sec:ifAnyGranted roles="ROLE_FICO_VISOR,ROLE_FICO_EJECUTOR">
        Facturación &amp; Cobranza<small><g:message code="saleOrder.list" args="[entityName]" /></small>
      </sec:ifAnyGranted>
      <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR">
        Operaciones / Facturación<small><g:message code="saleOrder.list" args="[entityName]" /></small>
      </sec:ifAnyGranted>
    </h1>
    <ol class="breadcrumb">
      <li><i class="fa fa-caret-square-o-up"></i>Compañía</li>
      <li class="active">Orden de Venta</li>
    </ol>
  </div>
<div id="edit-address" class="content scaffold-edit" role="main">
  <div class="portlet portlet-blue">
    <div class="portlet-heading">
      <div class="portlet-title">
        <br />
        <br />
      </div>
      <div class="clearfix"></div>
    </div>
    <div id="horizontalFormExample" class="panel-collapse collapse in">
      <div class="portlet-body">
        <g:if test="${flash.message}">
          <div class="alert alert-danger" role="alert">${flash.message}</div>
        </g:if>
        <g:if test="${messageSuccess}">
          <div class="well well-sm alert-success">${messageSuccess}</div>
        </g:if>
      <div class="table-responsive">
        <table class="table">
         <tr>
           <th>No. de Orden</th>
           <th>RFC</th>
           <th>Cliente</th>
           <th>Estatus</th>
           <th>Compañía</th>
           <th>Fecha de Creación</th>
           <th>Total</th>
          </tr>
          <g:if test="${saleOrders.isEmpty()}">
            <div class="alert alert-danger" role="alert">
              <g:message code="saleOrder.list.empty"/>
            </div>
          </g:if>
         <g:each in="${saleOrders.sort{it.dateCreated}}" var="sale">
         <tr class="${message(code: 'saleOrder.style.background.'+sale.status)}">
            <td class="text-center"><g:link action="show" id="${sale.id}">${sale.id}</g:link></td>
            <td>${sale.rfc}</td>
            <td>${sale.clientName}</td>
            <td><g:message code="saleOrder.status.${sale.status}" default="${sale.status}"/> </td>
            <td>${sale.company}</td>
            <td><g:formatDate format="dd-MM-yyyy hh:mm:ss" date="${sale.dateCreated}"/></td>
            <td class="text-right">${modulusuno.formatPrice(number: sale.total)}</td>
          </tr>
         </g:each>
       </table>
       <nav>
          <div class="pagination">
            <g:paginate class="pagination" controller="saleOrder" action="list" total="${saleOrderCount}" />
          </div>
        </nav>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>
