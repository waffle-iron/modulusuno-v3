<%! import com.modulus.uno.CompanyTaxRegime %>

<g:if test="${company.taxRegime == CompanyTaxRegime.MORAL}">
  <g:set var="labelNoExist" value="Debe agregar al menos un representante legal"/>
  <g:set var="labelNoDocuments" value="Debe agregar los documentos requeridos de los representantes"/>
</g:if><g:else>
  <g:set var="labelNoExist" value="Debe definir los datos de la Persona Física"/>
</g:else>

<div class="property-value" aria-labelledby="address-label">
  <g:if test="${!company.legalRepresentatives}">
    <h4><span class="label label-warning">${labelNoExist}</span></h4>
  </g:if>
  <g:if test="${company.taxRegime == CompanyTaxRegime.MORAL && company.legalRepresentatives && !legalRepresentativesAvilableWithDocuments}">
    <h4><span class="label label-warning">Debe agregar los documentos requeridos de los representantes</span></h4>
  </g:if>

  <g:if test="${company.taxRegime == CompanyTaxRegime.FISICA_EMPRESARIAL && !company.legalRepresentatives}">
    <sec:ifAnyGranted roles="ROLE_INTEGRADO">
      <div class="form-group">
        <div class="col-sm-6">
          <g:link controller="legalRepresentative" action="addRepresentative" class="btn btn-default">
            <g:message code="legalRepresentative.physical.myself" />
          </g:link>
        </div>
        <div class="col-sm-6">
          <g:link class="create" controller="user" action="legalRepresentative" params="[legal:true,company:"${company.id}"]" class="btn btn-default" >
            <g:message code="legalRepresentative.physical.create" />
          </g:link>
        </div>
      </div>
    </sec:ifAnyGranted>
  </g:if>

  <g:if test="${company.legalRepresentatives}">
    <g:each var="representante" in="${company.legalRepresentatives}">
      <div class="form-group">
        <g:if test="${company.taxRegime == CompanyTaxRegime.MORAL}">
        <label id="${legalRepresentatives}-label" class="col-sm-9 control-label">
        </g:if><g:else>
          <label id="${legalRepresentatives}-label" class="col-sm-5 control-label">
        </g:else>
          <g:link controller="user" action="edit" id="${representante.id}" params="[company:company.id]">
            ${representante.profile.name} ${representante.profile.lastName} ${representante.profile.motherLastName}
          </g:link>
        </label>
        <g:if test="${company.taxRegime == CompanyTaxRegime.MORAL}">
        <div class="col-sm-3">
          <sec:ifAnyGranted roles="ROLE_INTEGRADO">
              <g:if test="${representante.profile.documents*.status.contains(true)}">
                <g:link action="showDocumentLegalRepresentative" controller="uploadDocuments" params="[user:representante.id,companyId:company.id]" class="btn btn-default"><i class="fa fa-eye"></i></g:link>
              </g:if>
               <a href='${createLink(controller:"uploadDocuments",action:"create",params:[company:"${company.id}",user:"${representante.id}"])}' class="btn btn-default"><i class="fa fa-file"></i></a>
          </sec:ifAnyGranted>
        </div>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
          <div class="col-sm-3">
            <g:link action="showDocumentLegalRepresentative" controller="uploadDocuments" params="[user:representante.id,companyId:company.id]" class="btn btn-default"><i class="fa fa-eye"></i></g:link>
          </div>
        </sec:ifAnyGranted>
        </g:if>
      </div>
    </g:each>
  </g:if>
</div>
<br />
<g:if test="${company.taxRegime == CompanyTaxRegime.MORAL}">
<sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <li class="subList text-right">
    <g:link controller="legalRepresentative" action="index" class="btn btn-default" id="${company.id}"><i class="fa fa-plus"></i></g:link>
  </li>
</sec:ifAnyGranted>
</g:if>
