<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="bankAccount" in="${company.banksAccounts.sort{it.banco.name}}">
    <li class="subList">
      <g:link action="edit" controller="bankAccount" params="[company:company.id, companyBankAccount:true]" id="${bankAccount.id}">${bankAccount.accountNumber} - ${bankAccount.banco}</g:link> <g:if test="${bankAccount.concentradora}">Cuenta Concentradora</g:if>
    </li>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
<div class="text-right">
  <g:link action="create" controller="bankAccount" params="[company:company.id, companyBankAccount:true]" class="btn btn-default"><i class="fa fa-plus"></i></g:link>
  </div>
</sec:ifAnyGranted>
