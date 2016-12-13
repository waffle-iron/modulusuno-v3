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
      <div class="panel-body">
        <g:form action="cashFlow">
          <div class="col-md-4">
            <p>Desde el:</p>
            <g:datePicker id="startDate" name="startDate" value="${cashFlow.startDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
          </div>
          <div class="col-md-4">
            <p>Hasta el:</p>
            <g:datePicker id="endDate" name="endDate" value="${cashFlow.endDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
          </div>
          <div class="col-md-4 text-righ text-right">
            <g:actionSubmit class="btn btn-default" value="Consultar" action="cashFlow"/>
          </div>
        </g:form>
      </div>
    </div>
    <div class="panel panel-primary">
      <div class="panel-heading">Cobros</div>
      <div class="panel-body">
        <p><b>Total: ${modulusuno.formatPrice(number:cashFlow.totalCharges)}</b></p>
        <div class="text-right">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#detailCharges">Detalle</button>
        </div>
      </div>
      <div class="collapse" id="detailCharges">
        <div class="container-fluid">
          <div class="well">
            <div class="table-responsive">
              <table class="table table-condensed table-hover">
                <thead>
                  <tr>
                  <th class="text-center">Empresa</th>
                  <th class="text-center">Cliente</th>
                  <th class="text-center">Fecha Cobro</th>
                  <th class="text-center">Monto</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each var="charge" in="${cashFlow.listCharges.sort{it.fechaCobro}}">
                    <tr>
                      <td>${charge.company.bussinessName}</td>
                      <td>${charge.clientName}</td>
                      <td><g:formatDate format="dd-MM-yyyy" date="${charge.fechaCobro}"/></td>
                      <td>${modulusuno.formatPrice(number:charge.total)}</td>
                  </g:each>
                </tbody>
              </table>
            </div><!--table responsive-->
          </div>
        </div><!--container-->
      </div>
    </div>
    <div class="panel panel-primary">
      <div class="panel-heading">Pagos</div>
      <div class="panel-body">
        <p><b>Total: ${modulusuno.formatPrice(number:cashFlow.totalPayments)}</b></p>
        <div class="text-right">
          <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#detailPayments">Detalle</button>
        </div>
      </div>
      <div class="collapse" id="detailPayments">
        <div class="container-fluid">
          <div class="well">
            <div class="table-responsive">
              <table class="table table-condensed table-hover">
                <thead>
                  <tr>
                    <th class="text-center">Empresa</th>
                    <th class="text-center">Proveedor</th>
                    <th class="text-center">Fecha Pago</th>
                    <th class="text-center">Monto</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each var="pay" in="${cashFlow.listPayments.sort{it.fechaPago}}">
                  <tr>
                    <td>${pay.company.bussinessName}</td>
                    <td>${pay.providerName}</td>
                    <td><g:formatDate format="dd-MM-yyyy" date="${pay.fechaPago}"/></td>
                    <td>${modulusuno.formatPrice(number:pay.total)}</td>
                      </g:each>
                </tbody>
              </table>
            </div><!--table responsive-->
          </div>
        </div><!--container-->
      </div>
    </div>
</body>
</html>
