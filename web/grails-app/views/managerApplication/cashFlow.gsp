<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'accountStatement.label', default: 'AccountStatement')}" />
    <title><g:message code="manager.cashFlow.label" args="[entityName]" /></title>
 </head>
 <body>
   <div class="page-title">
     <h1>
     <i class="fa fa-money fa-3x"></i>
     <g:message code="manager.cashFlow.label"/>
     </h1>
     <ol class="breadcrumb">
       <li><i class="fa fa-caret-square-o-up"></i>Modulus UNO</li>
       <li class="active">Flujo de Efectivo</li>
     </ol>
   </div>
   <div class="panel panel-info">
     <div class="panel-heading">Período</div>
     <div class="panel-body">
       <div class="col-md-4">
        <p>Desde el:</p>
        <g:datePicker id="startDate" name="startDate" value="" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
       </div>
       <div class="col-md-4">
        <p>Hasta el:</p>
        <g:datePicker id="endDate" name="endDate" value="" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
       </div>
       <div class="col-md-4 text-righ text-right">
        <g:actionSubmit class="btn btn-default" value="Consultar" action="accountStatement"/>
       </div>
     </div>
   </div>
       <div class="col-md-6">
        <div class="panel panel-primary">
          <div class="panel-heading">Pagos</div>
          <div class="panel-body">
            <p>Total:</p>
            <div class="text-right">
              <g:link class="btn btn-primary" controller="PurchaseOrder" action="detailForPaymentInPeriod">Detalle</g:link>
            </div>
          </div>
        </div>
       </div>
       <div class="col-md-6">
        <div class="panel panel-primary">
          <div class="panel-heading">Cobros</div>
          <div class="panel-body">
            <p>Total:</p>
            <div class="text-right">
              <g:link class="btn btn-primary" controller="SaleOrder" action="detailForChargeInPeriod">Detalle</g:link>
            </div>
          </div>
        </div>
       </div>

</body>
</html>
