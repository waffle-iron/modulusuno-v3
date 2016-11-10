<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'saleOrder.label', default: 'SaleOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>Conciliación de órdenes de Facturación y Cobranza</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> ${company}</li>
        <li class="active">Listado de órdenes</li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Depósitos por conciliar</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
            <div class="portlet-body">

              <g:if test="${flash.message}">
               <div class="alert alert-danger">${flash.message}</div>
              </g:if>

              <table class="table table-condensed table-striped">
                <thead>
                  <tr>
                <th>&nbsp;</th>
                    <th>Monto</th>
                    <th>Fecha</th>
                    <th>Orden de Facturación</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <g:each in="${payments}" var="payment" status="i">
                  <g:form controller="payment" action="tieOrderWithPayment" id="${payment.id}">
                    <tr>
                      <td> ${i+1} </td>
                      <td> ${integradora.formatPrice(number: payment.amount)} </td>
                      <td> ${formatDate(date:payment.dateCreated, format:'dd-MMMM-yyyy HH:mm')} </td>
                      <td>
                        <g:select optionKey="id" name="saleOrder.id" from="${saleOrders.collect { if((it.total-payment.amount).abs()<1) it}.findResults{it} }" noSelection="['':'-Escoge un pago-']"/>
                      </td>
                      <td><button type="submit" class="btn btn-default btn-xs">Conciliar orden con pago</button></td>
                    </tr>
                  </g:form>
                  </g:each>
                </tbody>
              </table>

            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
