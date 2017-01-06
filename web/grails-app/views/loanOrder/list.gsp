<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanOrder.label', default: 'LoanOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>

    <div class="page-title">
      <h1>
      <i class="icon-prestamo fa-3x"></i>
        <sec:ifAnyGranted roles="ROLE_FICO_EJECUTOR">
          Préstamos<small><g:message code="loanOrder.list"/></small>
        </h1>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR">
          Operaciones / Préstamo<small><g:message code="loanOrder.list"/></small>
        </sec:ifAnyGranted>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanOrder.list"/></li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4><g:message code="loanOrder.list"/></h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="alert alert-danger" role="alert">${flash.message}</div>
              </g:if>
              <g:if test="${messageSuccess}">
                <div class="well well-sm alert-success">${messageSuccess}</div>
              </g:if>
              <g:render template="table" model="[loanOrderList:loanOrderList]"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
