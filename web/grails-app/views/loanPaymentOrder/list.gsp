<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanPaymentOrder.label', default: 'LoanOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>

    <div class="page-title">
    <h1>
      <i class="icon-pago fa-3x"></i>
      Pago de Préstamos
      <small><g:message code="loanPaymentOrder.list"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanPaymentOrder.list"/></li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4><g:message code="loanPaymentOrder.list"/></h4>
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
              <g:render template="table"/>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
