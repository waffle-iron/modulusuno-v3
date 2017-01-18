<!DOCTYPE html>
<%! import java.text.SimpleDateFormat %>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'accountStatement.label', default: 'AccountStatement')}" />
    <title><g:message code="manager.pastDuePortfolio.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
      <i class="fa fa-money fa-3x"></i>
      <g:message code="manager.pastDuePortfolio.label"/>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i>Modulus UNO</li>
        <li class="active"><g:message code="manager.pastDuePortfolio.label"/></li>
      </ol>
      <div class="panel panel-primary">
        <div class="panel-heading">Cartera Vencida</div>
        <div class="panel-body">
          <div class="container-fluid">
            <div class="col-md-3 text-center">
              <g:link class="btn btn-info" action="pastDuePortfolioForDays" params="[days:30]">30 días</g:link>
            </div>
            <div class="col-md-3 text-center">
              <g:link class="btn btn-warning" action="pastDuePortfolioForDays" params="[days:60]">60 días</g:link>
            </div>
            <div class="col-md-3 text-center">
              <g:link class="btn btn-warning" action="pastDuePortfolioForDays" params="[days:90]">90 días</g:link>
            </div>
            <div class="col-md-3 text-center">
              <g:link class="btn btn-danger" action="pastDuePortfolioForDays" params="[days:120]">120 días y más</g:link>
            </div>
          </div>
        </div>
      </div> <!-- cabecera -->

      <g:if test="${days}">
      <div class="panel panel-primary">
        <div class="panel-body">
          <div class="container-fluid">
          <div class="row">
            <div class="alert alert-${alert}">
              Cartera vencida de ${days} días ${days==120? "y más" : ""}
            </div>
          </div>
          </div>
        </div>
      </div>
      </g:if>

    </div>
  </body>
</html>
