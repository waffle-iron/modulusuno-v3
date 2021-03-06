<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="address" in="${company.addresses}">
      <sec:ifAnyGranted roles="ROLE_CORPORATIVE">
        <li class="subList">${address}</li>
      </sec:ifAnyGranted>
      <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_LEGAL_REPRESENTATIVE_VISOR">
        <li class="subList"><g:link controller="address" action="edit" id="${address.id}" >${address}</g:link></li>
      </sec:ifAnyGranted>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
  <div class="text-right">
  	<g:link action="create" controller="address" class="btn btn-default"><i class="fa fa-plus"></i></g:link>
  </div>
</sec:ifAnyGranted>
