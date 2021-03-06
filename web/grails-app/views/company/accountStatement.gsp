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
       <i class="fa fa-university fa-2x"></i>
       <g:message code="Estado de Cuenta"/>
       <small>${accountStatement.company.bussinessName} - ${accountStatement.company.rfc}</small>
     </h1>
     <ol class="breadcrumb">
       <li><i class="fa fa-caret-square-o-up"></i>Compañia</li>
       <li class="active">Estado de Cuenta</li>
     </ol>
   </div>
   <div class="row">
     <div class="col-lg-8 col-md-7 col-sm-12">
   <h2>${accountStatement.company.bussinessName} - ${accountStatement.company.rfc}</h2>
   <div class="table-responsive">
      <table class="table table-condensed">
        <tr>
          <th>Moneda / Saldo</th>
          <th>En Tránsito</th>
          <th>Disponible</th>
          <th>Total</th>
        </tr>
        <tr>
          <td><strong>Pesos</strong></td>
          <td>${modulusuno.formatPrice(number: accountStatement.balanceTransiting)}</td>
          <td>${modulusuno.formatPrice(number: (accountStatement.balance.balance-accountStatement.balanceTransiting))}</td>
          <td>${modulusuno.formatPrice(number: accountStatement.balance.balance)}</td>
        </tr>
        <tr>
          <td><strong>Dólares</strong></td>
          <td >${modulusuno.formatPrice(number: 0.00)}</td>
          <td >${modulusuno.formatPrice(number: 0.00)}</td>
          <td >${modulusuno.formatPrice(number: usd)}</td>
        </tr>
      </table>
   </div>
     </div>
     <div class="col-lg-4 col-md-5 col-sm-12">
        <div class="portlet">
        <div class="portlet-heading">
          <h2>Consultas</h2>
        </div>
        <div class="portlet-body">
     <g:form controller="Company" action="accountStatement" class="form-horizontal">
        <g:hiddenField name="company" value="${accountStatement.company.id}"/>
        <g:hiddenField name="query" value="1"/>
        <g:if test="${flash.message}">
          <div class="error alert alert-danger" role="alert">${flash.message}</div>
        </g:if>
        <div class="row">
            <div class="col-md-12">
              <p>Desde el:</p>
              <g:datePicker id="startDate" name="startDate" value="${accountStatement.startDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
            </div>
            <div class="col-md-12">
              <p>Hasta el:</p>
              <g:datePicker id="endDate" name="endDate" value="${accountStatement.endDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
            </div>
            <div class="col-md-12 text-right">
              <div style="margin-top:1em;">
                <g:actionSubmit class="btn btn-default" value="Consultar" action="accountStatement"/>
              </div>
            </div>
          </div>
        </div>
     </g:form>

   </div>
     </div>
   </div>

   <div class="row">
     <div class="col-md-12">
        <div class="portlet">
        <div class="portlet-heading">
          <div class="table-responsive">
            <table class="table">
              <tr>
                <th><h2>Resultado de la consulta</h2></th>
                <th class="text-right">
                  <br/>
                  <g:link class="btn btn-default" action="generatePdfForAccountStatement" params="[company:accountStatement.company.id, startDate:accountStatement.startDate, endDate: accountStatement.endDate]">
                    <i class="fa fa-file-pdf-o"></i> PDF
                  </g:link>
                  <g:link class="btn btn-default" action="generateXlsForAccountStatement" params="[company:accountStatement.company.id, startDate:accountStatement.startDate, endDate: accountStatement.endDate]">
                    <i class="fa fa-file-excel-o"></i> XLS
                  </g:link>
                </th>
              </tr>
            </table>
          </div>
          <div class="portlet-body">
              <div class="table-responsive">
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
                          <g:message code="company.accountStatement.TransactionType.${mov.transactionType}" default="${mov.transactionType}"/>
                        </td>
                        <td>${mov.reference?:""}</td>
                        <td>
                          <g:if test="${mov.type.equals('CREDIT')}">
                            ${modulusuno.formatPrice(number: mov.amount)}
                          </g:if>
                        </td>
                        <td>
                          <g:if test="${mov.type.equals('DEBIT')}">
                            ${modulusuno.formatPrice(number: mov.amount)}
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
                        <td>${modulusuno.formatPrice(number: mov.balance)}</td>
                      </tr>
                     </g:each>
                   </table>
              </div>
          </div>
        </div>
     </div>
   </div>



</body>
</html>


