<div class="table-responsive">
  <table class="table">
  <tr>
    <th class="text-center"><g:message code="feesReceipt.id" /></th>
    <th class="text-center"><g:message code="feesReceipt.collaboratorName" /></th>
    <th class="text-center"><g:message code="feesReceipt.amount" /></th>
    <th class="text-center"><g:message code="feesReceipt.iva" /></th>
    <th class="text-center"><g:message code="feesReceipt.isr" /></th>
    <th class="text-center"><g:message code="feesReceipt.ivaWithHolding" /></th>
    <th class="text-center"><g:message code="feesReceipt.status" /></th>
    <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
      <th class="text-center">Autorizaciones</th>
      <th class="text-center">Por Autorizar</th>
    </sec:ifAnyGranted>
  </tr>
  <g:if test="${feesReceiptList.empty}">
    <div class="alert alert-danger" role="alert">
      <g:message code="feesReceipt.list.empty"/>
    </div>
  </g:if>

  <g:each in="${feesReceiptList}" var="feesReceipt">
  <tr class="${message(code: 'feesReceipt.style.background.'+feesReceipt.status)}">
    <td class="text-center"><g:link action="show" id="${feesReceipt.id}">${feesReceipt.id}</g:link></td>
    <td>${feesReceipt.collaboratorName}</td>
    <td class="text-right">${modulusuno.formatPrice(number: feesReceipt.amount)}</td>
    <td class="text-right">${modulusuno.formatPrice(number: feesReceipt.iva)}</td>
    <td class="text-right">${modulusuno.formatPrice(number: feesReceipt.isr)}</td>
    <td class="text-right">${modulusuno.formatPrice(number: feesReceipt.ivaWithHolding)}</td>
    <td class="text-center">${feesReceipt.status}</td>
    <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
      <td class="text-center">${feesReceipt.authorizations?.size() ?: 0}</td>
      <td class="text-center">${feesReceipt.company.numberOfAuthorizations - (feesReceipt.authorizations?.size() ?: 0)}</td>
    </sec:ifAnyGranted>
  </tr>
  </g:each>
  </table>
</div>
<div class="pagination">
  <g:paginate total="${feesReceiptCount ?: 0}" />
</div>

