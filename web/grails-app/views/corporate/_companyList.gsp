 <%! import com.modulus.uno.CompanyStatus %>
<div>
  <table class="table">
    <thead>
      <th><g:message code="company.rfc" /></th>
      <th><g:message code="company.bussinessName" /></th>
      <th><g:message code="company.grossAnnualBilling" /></th>
      <th><g:message code="company.status" /></th>
    </thead>
    <tbody>
      <g:each var="company" in="${companies}">
      <tr>
        <td>
          <g:link controller="company" action="show" id="${company.id}">${company.rfc}</g:link>
        </td>
        <td>${company.bussinessName}</td>
        <td><modulusuno:formatPrice number="${company.grossAnnualBilling}"/></td>
        <td><h4><modulusuno:statusForCompany status="${company.status}"/></h4></td>
      </tr>
      </g:each>
    </tbody>
  </table>
</div>
