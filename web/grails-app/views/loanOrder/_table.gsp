<div class="table-responsive">
  <table class="table">
    <tr>
      <th>No. de Orden</th>
      <th>Empresa</th>
      <th>Monto</th>
      <th>Plazo</th>
      <th>Tasa</th>
      <th>Estatus</th>
      <th>Deudor</th>
    </tr>
    <g:if test="${!loanOrderList || loanOrderList.empty}">
      <div class="alert alert-danger" role="alert">
        <g:message code="loanOrder.list.empty"/>
      </div>
    </g:if>
    <g:each in="${loanOrderList}" var="loanOrder">
    <tr class="${message(code: 'loanOrder.style.background.'+loanOrder.status)}">
      <td class="text-center"><g:link action="show" id="${loanOrder.id}">${loanOrder.id}</g:link></td>
      <td>${loanOrder.company}</td>
      <td><modulusuno:formatPrice number="${loanOrder.amount}"/></td>
      <td>${loanOrder.term}</td>
      <td>${loanOrder.rate}</td>
      <td><g:message code="${loanOrder.status.code}"/></td>
      <td>${loanOrder.creditor}</td>
    </tr>
    </g:each>
  </table>
</div>
