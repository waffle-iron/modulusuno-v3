<div class="table-responsive">
  <table class="table">
   <tr>
     <th>Compañía</th>
     <th>Monto</th>
     <th>Fecha de Creación</th>
     <th>Estatus</th>
   </tr>
   <g:each in="${payments.sort{it.dateCreated}}" var="pay">
   <tr class="${message(code: 'payment.style.background.'+pay.status)}">
      <td>
        <g:if test="${params.status}">
          <g:link action="show" id="${pay.id}" params="[status:"${params.status}"]">${pay.company}</g:link></td>
        </g:if>
        <g:else>
          <g:link action="show" id="${pay.id}">${pay.company}</g:link></td>
        </g:else>
      <td>${integradora.formatPrice(number: pay.amount)}</td>
      <td><g:formatDate format="dd-MM-yyyy hh:mm" date="${pay.dateCreated}"/></td>
      <td>
        <g:message code="payment.${pay.status}"/>
      </td>
    </tr>
   </g:each>
 </table>
 <nav>
  <ul class="pagination">
    <g:paginate class="pagination" controller="payment" action="index" total="${paymentsCount}" />
  </ul>
 </nav>
</div>

