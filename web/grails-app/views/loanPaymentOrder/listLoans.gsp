<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanPaymentOrder.label', default: 'LoanPaymentOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
      <i class="icon-pago fa-3x"></i>
      Pagos a préstamos / Listado de Pagos
      <small><g:message code="loanPaymentOrder.chooseLoan"/></small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanPaymentOrder.chooseLoan"/></li>
      </ol>
    </div>
    <div class="row">

    <div class="col-lg-12">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Seleccione el préstamo</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
            <g:if test="${flash.message}">
              <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>

            <div class="table-responsive">
              <table class="table">
                <tr>
                  <th class="text-center">Acreedor</th>
                  <th class="text-center">Monto</th>
                  <th class="text-center">Plazo</th>
                  <th class="text-center">Tasa Anual</th>
                </tr>
                <g:each in="${loanOrderList}" var="loanOrder">
                  <tr>
                    <td>
                      <g:if test="${payments}">
                      <g:link action="loanOrderPayments" id="${loanOrder.id}">
                        ${loanOrder.company}
                      </g:link>
                      </g:if><g:else>
                      <g:link action="create" params="[idLoanOrder : loanOrder.id]">
                        ${loanOrder.company}
                      </g:link>
                      </g:else>
                    </td>
                    <td class="text-right">${modulusuno.formatPrice(number: loanOrder.amount)}</td>
                    <td class="text-center">${loanOrder.term}</td>
                    <td class="text-center">${loanOrder.rate}</td>
                  </tr>
                </g:each>
              </table>
            </div>

          </div>
        </div>
      </div>
    </div>

    </div>
  </body>
</html>
