<%! import com.modulus.uno.CompanyTaxRegime %>
<label>${message(code:"company.taxRegime")} *</label>
  <g:select name="taxRegime" from="${CompanyTaxRegime.values()}" class="form-control" value="${company.taxRegime}" valueMessagePrefix="CompanyTaxRegime"/>
<f:field bean="company" property="bussinessName" label="${message(code:"company.bussinessName")}*" wrapper="home"/>
<f:field bean="company" property="webSite" label="${message(code:"company.webSite")}" wrapper="home" />
<f:field bean="company" property="employeeNumbers" label="${message(code:"company.employeeNumbers")}*" wrapper="home" />
<f:field bean="company" property="grossAnnualBilling" label="${message(code:"company.grossAnnualBilling")}*"wrapper="home" />
<div class="form-group">
  <label class="control-label"><g:message code="company.rfc" /><span class="required-indicator">*</span></label>
  	<input id="rfc" class="form-control" name="rfc" value="${company.rfc}" required="true" ${(edit)?"disabled='disabled'":""} />
</div>
<f:field bean="company" property="numberOfAuthorizations" label="${message(code:"company.numberOfAuthorizations")}*" wrapper="home" />
