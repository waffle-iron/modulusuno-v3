<%! import com.modulus.uno.MovimientoBancarioType %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios')}" />
        <title>Listado de Movimientos Bancarios</title>
    </head>
    <body>
        <div id="show-movimientosBancarios" class="content scaffold-show" role="main">
          <h1><g:message code="default.show.label" args="[entityName]" /> ${movimientosBancarios.first().cuenta}  Saldo Total: <modulusuno:amountAccountToday id="${movimientosBancarios.first().cuenta.id}" /></h1>
            <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table class="table table-striped">
              <tr>
                <th scope="col">Fecha</th>
                <th scope="col">Referencia</th>
                <th scope="col">Abono</th>
                <th scope="col">Cargo</th>
                <th scope="col">Tipo de Transacción</th>
                <th scope="col">Concepto</th>
              </tr>
            <g:each in="${movimientosBancarios}" var="movimiento">
              <tr>
                <td>
                  ${movimiento.dateEvent.format('dd/MMM/yyyy')}
                </td>
                <td>${movimiento.reference}</td>
                <td>
                  <g:if test="${movimiento.type == MovimientoBancarioType.CREDITO}">
                    $ ${movimiento.amount}
                  </g:if>
                </td>
                <td>
                  <g:if test="${movimiento.type == MovimientoBancarioType.DEBITO}">
                    $ ${movimiento.amount}
                  </g:if>
                </td>
                <td>
                  <g:if test="${movimiento.type == MovimientoBancarioType.CREDITO}">
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
                <td>${movimiento.concept}</td>
              </tr>
            </g:each>
        </div>
    </body>
</html>
