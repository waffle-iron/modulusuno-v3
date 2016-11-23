<div class="table-responsive">
  <table class="table">
   <tr>
     <th>No. de Orden</th>
     <th>Compañía</th>
     <th>Monto</th>
     <th>Fecha de Creación</th>
     <th>Estatus</th>
   </tr>
    <g:if test="${flash.message}">
      <div class="alert alert-danger" role="alert">${flash.message}</div>
    </g:if>
    <g:if test="${params.messageSuccess}">
      <div class="well well-sm alert-success">${params.messageSuccess}</div>
    </g:if>
    <g:if test="${depositOrder.isEmpty()}">
      <div class="alert alert-danger" role="alert">
        <g:message code="depositOrder.list.empty"/>
      </div>
    </g:if>
   <g:each in="${depositOrder.sort{it.dateCreated}}" var="dep">
   <tr class="${message(code: 'depositOrder.style.background.'+dep.status)}">
      <td class="text-center">
        <g:if test="${params.status}">
          <g:link action="show" id="${dep.id}" params="[status:"${params.status}"]">${dep.id}</g:link>
        </g:if>
        <g:else>
          <g:link action="show" id="${dep.id}">${dep.id}</g:link>
        </g:else>
      </td>
      <td>
        ${dep.company}
      </td>
      <td>${modulusuno.formatPrice(number: dep.amount)}</td>
      <td><g:formatDate format="dd-MM-yyyy hh:mm" date="${dep.dateCreated}"/></td>
      <td>
        <g:message code="depositOrder.status.${dep.status}"/>
      </td>
    </tr>
   </g:each>
 </table>
 <nav>
  <ul class="pagination">
    <g:paginate class="pagination" controller="depositOrder" action="list" total="${depositOrderCount}" />
  </ul>
 </nav>
</div>

