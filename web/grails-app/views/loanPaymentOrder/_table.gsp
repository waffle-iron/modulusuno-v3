<g:if test="${loanPaymentOrderList}">
<sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
<div class="table-responsive">
  <table class="table">
    <tr>
      <th>Acreedor</th>
      <th>Monto</th>
      <th>Plazo</th>
      <th>Tasa Anual</th>
    </tr>
    <g:set value="${loanPaymentOrderList[0].loanOrder}" var="loanOrder"/>
    <tr>
      <td>${loanOrder.company}</td>
      <td><modulusuno:formatPrice number="${loanOrder.amount}"/></td>
      <td>${loanOrder.term}</td>
      <td>${loanOrder.rate}</td>
    </tr>
  </table>
</div>
</sec:ifAnyGranted>

<div class="table-responsive">
  <table class="table">
    <tr>
      <th>No. de Orden</th>
      <th>Fecha</th>
      <th>Monto</th>
      <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR, ROLE_ADMIN, ROLE_ADMIN_IECCE">
        <th>Acreedor</th>
      </sec:ifAnyGranted>
      <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
        <th>Deudor</th>
      </sec:ifAnyGranted>
      <th>Estatus</th>
    </tr>
    <g:each in="${loanPaymentOrderList}" var="loanPaymentOrder">
    <tr class="${message(code: 'loanPaymentOrder.style.background.'+loanPaymentOrder.status)}">
      <td class="text-center">
        <g:link action="show" id="${loanPaymentOrder.id}">
          ${loanPaymentOrder.id}
        </g:link>
      </td>
      <td>
        <g:formatDate format="dd-MM-yyyy" date="${loanPaymentOrder.datePayment}"/>
      </td>
      <td><modulusuno:formatPrice number="${loanPaymentOrder.amountToCapital + loanPaymentOrder.amountInterest + loanPaymentOrder.amountIvaInterest}"/></td>
      <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR, ROLE_ADMIN, ROLE_ADMIN_IECCE">
        <td>${loanPaymentOrder.loanOrder.company}</td>
      </sec:ifAnyGranted>
      <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
        <td>${loanPaymentOrder.loanOrder.creditor}</td>
      </sec:ifAnyGranted>
      <td><g:message code="${loanPaymentOrder.status.code}"/></td>
    </tr>
    </g:each>
  </table>
</div>
<div class="pagination">
  <g:paginate total="${loanPaymentOrderCount ?: 0}" />
</div>
</g:if><g:else>
  <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
    <div class="alert alert-danger">El préstamo seleccionado no tiene pagos registrados</div>
  </sec:ifAnyGranted>
  <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
    <div class="alert alert-danger">No hay pagos a préstamos por autorizar</div>
  </sec:ifAnyGranted>
  <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
    <div class="alert alert-danger">No hay pagos a préstamos por revisar</div>
  </sec:ifAnyGranted>
</g:else>
