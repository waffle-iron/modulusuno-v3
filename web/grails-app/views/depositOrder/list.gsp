<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'DepositOrder')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
  <div class="page-title">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE, ROLE_INTEGRADO_OPERADOR">
   <h1>
     <i class="icon-deposito fa-3x"></i>
     Operaciones / Depósito
     <small><g:message code="depositOrder.list" args="[entityName]" /></small>
   </h1>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
   <h1>
     <i class="icon-deposito fa-3x"></i>
      Depósitos
     <small>Listado de Depósitos</small>
   </h1>
</sec:ifAnyGranted>

   <ol class="breadcrumb">
   <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
    <li class="active">Órdenes de Depósito</li>
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
    <div id="defaultPortlet" class="panel-collapse collapse in">
      <div class="portlet-body">
        <div id="list-depositOrder" class="content portlet-blue" role="main">
          <g:render template="list"/>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</body>
</html>
