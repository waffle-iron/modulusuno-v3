 <div class="property-value" aria-labelledby="${label}-label">
 <div class="text-right">
  <g:if test="${company.documents}">
      <g:link action="show" controller="uploadDocuments" params="[companyId:company.id]" class="btn btn-default"><i class="fa fa-eye"></i></g:link>
  </g:if>
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
      <g:link  controller="uploadDocuments" params="[company:company.id]" class="btn btn-default"><i class="fa fa-plus"></i></g:link>
  </sec:ifAnyGranted>
</div>
<br />
<br />
