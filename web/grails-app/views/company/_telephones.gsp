<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="telephone" in="${company.telephones}">
      <g:if test="ROLE_CORPORATIVE">
        ${telephones.toString()}
      </g:if>
      <g:else>
        <li class="subList"><g:link controller="telephone" action="editForCompany" id="${telephone.id}" >${telephone.toString()}</g:link></li>
      </g:else>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
  <div class="text-right">
  	<g:link action="createForCompany" controller="telephone" class="btn btn-default"><i class="fa fa-plus"></i></g:link>
  </div>
</sec:ifAnyGranted>
