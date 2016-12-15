<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'accountStatement.label', default: 'AccountStatement')}" />
    <title><g:message code="manager.pendingAccounts.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
      <i class="fa fa-money fa-3x"></i>
      <g:message code="manager.pendingAccounts.label"/>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i>Modulus UNO</li>
        <li class="active"><g:message code="manager.pendingAccounts.label"/></li>
      </ol>
    </div>
    <div class="panel panel-info">
      <div class="panel-body">
        <g:form action="pendingAccounts">
          <div class="col-md-4">
            <p>Desde el:</p>
            <g:datePicker id="startDate" name="startDate" value="${pendingAccounts.startDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
          </div>
          <div class="col-md-4">
            <p>Hasta el:</p>
            <g:datePicker id="endDate" name="endDate" value="${pendingAccounts.endDate}" precision="day" years="${2016..new Date()[Calendar.YEAR]}" required=""/>
          </div>
          <div class="col-md-2 text-righ text-right">
            <g:actionSubmit class="btn btn-default" value="Consultar" action="pendingAccounts"/>
          </div>
        </g:form>
        <div class="col-md-2 text-righ text-right">
          <g:link class="btn btn-default" action="generateXlsForPendingAccounts" params="[startDate:pendingAccounts.startDate, endDate:pendingAccounts.endDate]">
            <i class="fa fa-file-excel-o"></i> XLS
          </g:link>
        </div>
      </div>
    </div>
    <div class="panel panel-primary">
      <div class="panel-heading">Cuentas por Cobrar</div>
      <div class="panel-body">
        <p><b>Total: ${modulusuno.formatPrice(number:pendingAccounts.totalCharges)}</b></p>
        <p><b>Total vencido: ${modulusuno.formatPrice(number:pendingAccounts.totalExpiredCharges)}</b></p>
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
                  <th class="text-center">Cliente</th>
                  <th class="text-center">Fecha Cobro</th>
                  <th class="text-center">Monto</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each var="charge" in="${pendingAccounts.listCharges.sort{it.fechaCobro}}">
                    <tr>
                      <td>${charge.clientName}</td>
                      <td class="text-center">
                        <button type="button" class="btn btn-link" data-toggle="modal" data-target="#changeDateChargeModal" data-whatever="${charge.id}">
                          <g:formatDate format="dd-MM-yyyy" date="${charge.fechaCobro}"/>
                        </button>
                      </td>
                      <td class="text-right">${modulusuno.formatPrice(number:charge.total)}</td>
                  </g:each>
                </tbody>
              </table>
            </div><!--table responsive-->
            <!-- modal change date -->
            <div class="modal fade" id="changeDateChargeModal" tabindex="-1" role="dialog" aria-labelledby="changeDateChargeModalLabel">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="changeDateChargeModalLabel">Cambiar fecha</h4>
                  </div>
                  <div class="modal-body">
                    <form>
                      <div class="form-group">
                        <label for="recipient-name" class="control-label">No. de Orden:</label>
                        <input type="text" class="form-control" id="orderId">
                      </div>
                      <div class="form-group">
                        <label for="message-text" class="control-label">Nueva Fecha:</label>
                        <input type="text" id="datepickerCharge" name="fechaCobro" required="required">
                      </div>
                    </form>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button type="button" class="btn btn-primary">Cambiar</button>
                  </div>
                </div>
              </div>
            </div>
            <!-- modal change date end -->
          </div>
        </div><!--container-->
      </div>
    </div>
    <div class="panel panel-primary">
      <div class="panel-heading">Cuentas por Pagar</div>
      <div class="panel-body">
        <p><b>Total: ${modulusuno.formatPrice(number:pendingAccounts.totalPayments)}</b></p>
        <p><b>Total vencido: ${modulusuno.formatPrice(number:pendingAccounts.totalExpiredPayments)}</b></p>
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
                    <th class="text-center">Proveedor</th>
                    <th class="text-center">Fecha Pago</th>
                    <th class="text-center">Monto</th>
                  </tr>
                </thead>
                <tbody>
                  <g:each var="pay" in="${pendingAccounts.listPayments.sort{it.fechaPago}}">
                  <tr>
                    <td>${pay.providerName}</td>
                    <td class="text-center"><g:formatDate format="dd-MM-yyyy" date="${pay.fechaPago}"/></td>
                    <td class="text-right">${modulusuno.formatPrice(number:pay.total)}</td>
                      </g:each>
                </tbody>
              </table>
            </div><!--table responsive-->
          </div>
        </div><!--container-->
      </div>
    </div>
    <asset:javascript src="pendingAccounts/pendingAccounts.js"/>
</body>
</html>