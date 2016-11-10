<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'accountStatement.label', default: 'AccountStatement')}" />
    <title><g:message code="default.accountStatement.label" args="[entityName]" /></title>
 </head>
 <body>
   <div class="page-title">
     <h1> 
     <i class="fa fa-balance-scale fa-3x"></i>
     <g:message code="Estado de Cuenta de la Integradora"/> 
     <small>Cuenta de <g:message code="manager.accountStatement.type.${accountType}" default="${accountType}"/></small>
     </h1>
     <ol class="breadcrumb">
       <li><i class="fa fa-caret-square-o-up"></i>IECCE</li>
       <li class="active">Estado de Cuenta de <g:message code="manager.accountStatement.type.${accountType}" default="${accountType}"/></li>
     </ol>
   </div>
   <div class="table-responsive">
      <table class="table table-condensed">
        <tr>
          <th style="text-align:center">Moneda / Saldo</th>
          <th style="text-align:center">En Tránsito</th>
          <th style="text-align:center">Disponible</th>
          <th style="text-align:center">Total</th>
        </tr>
        <tr>
          <th>Pesos</th>
          <td style="text-align:right"><h3><span class="label label-warning">${integradora.formatPrice(number: accountStatement.balanceTransiting)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-primary">${integradora.formatPrice(number: (accountStatement.balance.balance-accountStatement.balanceTransiting))}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-default">${integradora.formatPrice(number: accountStatement.balance.balance)}</span></h3></td>
        </tr>
        <tr>
          <th>Dólares</th>
          <td style="text-align:right"><h3><span class="label label-warning">${integradora.formatPrice(number: 0.00)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-primary">${integradora.formatPrice(number: 0.00)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-default">${integradora.formatPrice(number: 0.00)}</span></h3></td>
        </tr>
      </table>
   </div>

   <div class="panel panel-info">
   <div class="table-responsive">
     <g:form action="accountStatement${accountType}" class="form-horizontal">
        <g:if test="${flash.message}">
          <div class="error alert alert-danger" role="alert">${flash.message}</div>
        </g:if>
        <table class="table">
          <tr>
            <td><label>Del:</label><g:datePicker id="startDate" name="startDate" value="${accountStatement.startDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/></td>
            <td><label>Al:</label><g:datePicker id="endDate" name="endDate" value="${accountStatement.endDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/></td>
            <td><g:actionSubmit class="btn btn-primary" value="Consultar" action="accountStatement${accountType}"/></td>
          </tr>
        </table>
     </g:form>
   </div>
   </div>
   <table class="table">
     <tr>
       <th>Fecha</th>
       <th>Tipo</th>
       <th>Id de Transacción</th>
       <th>Abono</th>
       <th>Cargo</th>
       <th></th>
       <th>Saldo</th>
     </tr>
     <g:each in="${accountStatement.transactions}" var="mov">
      <tr>
        <td><g:formatDate format="dd-MM-yyyy hh:mm:ss" date="${new Date(mov.timestamp)}"/></td>
        <td>
          <g:message code="company.accountStatement.TransactionType.${mov.adminType}" default="${mov.adminType}"/>
        </td>
        <td>${mov.reference?:""}</td>
        <td>
          <g:if test="${mov.type.equals('CREDIT')}">
            ${integradora.formatPrice(number: mov.amount)}
          </g:if>
        </td>
        <td>
          <g:if test="${mov.type.equals('DEBIT')}">
            ${integradora.formatPrice(number: mov.amount)}
          </g:if>
        </td>
        <td>
          <g:if test="${mov.type.equals('CREDIT')}">
              <span class="label label-success">
                <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
              </span>
          </g:if>
          <g:else>
              <span class="label label-danger">
                <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
              </span>
         </g:else>
        </td>
        <td>${integradora.formatPrice(number: mov.balance)}</td>
      </tr>
     </g:each>
   </table>
</body>
</html>


