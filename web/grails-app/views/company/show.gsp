 <%! import com.modulus.uno.CompanyStatus %>
 <%! import com.modulus.uno.CompanyTaxRegime %>
 <!DOCTYPE html>
 <html>
 <head>
  <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
  <asset:stylesheet src="company/show.css" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <div class="row">
    <!-- BEGIN PAGE TITLE -->
    <div class="page-title">
      <h1>
        <i class="fa fa-info-circle fa-3x"></i>
        <g:message code="company.show"/>
        <small>Información de la Compañia</small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="company.show"/></li>
      </ol>
    </div>
    <!-- END OF PAGE TITLE -->
  </div>

  <div class="row">
    <div class="col-md-6">
      <div class="portlet portlet-default">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Datos de la Empresa</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
            <form class="form-horizontal" role="form">
             <div class="form-group">
               <label id="taxRegime-label" class="col-sm-12 control-label">
                 <g:message code="company.taxRegime"/>
               </label>
               <div class="col-sm-12"><g:message code="${company.taxRegime}"/></div>
             </div>
             <f:display bean="company" property="bussinessName" wrapper="describe" /></b>
             <f:display bean="company" property="webSite"  wrapper="describe" />
             <f:display bean="company" property="employeeNumbers" wrapper="describe" />
             <f:display bean="company" property="numberOfAuthorizations" wrapper="describe" />
             <div class="form-group">
              <label id="grossAnnualBilling-label" class="col-sm-12 control-label">Facturacion Anual Bruta</label>
              <div class="col-sm-12">
               ${modulusuno.formatPrice(number:company.grossAnnualBilling)}
             </div>
           </div>
           <g:if test="${balance != null}">
           <div class="form-group">
            <label id="grossAnnualBilling-label" class="col-sm-12 control-label">Balance de la Compañia en MXN</label>
            <div class="col-sm-12">
              ${modulusuno.formatPrice(number:balance)}
            </div>
          </div>
        </g:if>
        <g:if test="${usd != null}">
        <div class="form-group">
          <label id="grossAnnualBilling-label" class="col-sm-12 control-label">Balance de la Compañia en USD</label>
          <div class="col-sm-12">
            ${modulusuno.formatPrice(number:usd)}
          </div>
        </div>
      </g:if>
      <g:if test="${company.accounts}">
      <div class="form-group">
        <label id="grossAnnualBilling-label" class="col-sm-12 control-label">Cuenta STP</label>
        <div class="col-sm-12">
          ${company.accounts.first().stpClabe}
        </div>
      </div>
    </g:if>
    <f:display bean="company" property="rfc" wrapper="describe" />
  </form>
  <div class="property-value">
    <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:if test="${available}">
    <g:link controller="requestCompany" action="create" class="btn btn-success btn-block" params="[companyId:company.id]">Enviar mi Solicitud</g:link>
  </g:if>
  <g:else>
  <div class="alert alert-info" role="alert">
    <b><g:message code="company.status.message.${company.status}" /></b>
  </div>
</g:else>
<g:if test="${company.status == CompanyStatus.REJECTED}">
<g:link action="rejected" class="btn btn-default" id="${company.id}">Revisar Razones de Rechazo</g:link>
</g:if>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN">
<g:if test="${company.status == CompanyStatus.VALIDATE}">
<g:link controller="managerApplication" action="accepted" class="btn btn-success" params="[companyId:company.id]">Aprobar Solicitud</g:link>
<g:link controller="managerApplication" action="invalid" class="btn btn-danger" params="[companyId:company.id]">Rechazar Solicitud</g:link>
</g:if>
</sec:ifAnyGranted>
</div>
</div>
</div>
<div class="portlet-footer">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <div class="text-right">
    <g:link class="btn btn-default" action="edit" resource="${this.company}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
  </div>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN_IECCE">
<div class="text-right">
  <g:link class="btn btn-default" controller="commission" action="index" params="[companyId:company.id]">Listar comisiones</g:link>
</div>
</sec:ifAnyGranted>
</div>
</div>
</div>
<sec:ifAnyGranted roles="ROLE_ADMIN">
<div class="col-md-6">
  <div class="portlet portlet-blue">
    <div class="portlet-heading">
      <div class="portlet-title">
        <h4>Operaciones</h4>
      </div>
      <div class="clearfix"></div>
    </div>
    <div id="defaultPortlet" class="panel-collapse collapse in">
      <div class="portlet-body">

        <form class="form-horizontal" role="form">
          <g:if test="${company.status == CompanyStatus.ACCEPTED}">
          <g:render template="clientsProviders" />
          <g:render template="products" />
          <g:render template="depositOrder" />
          <g:render template="saleOrder" />
          <g:render template="purchaseOrder" />
          <g:render template="removalOrder" />
          <br />
          <g:link action="accountStatement" controller="company" params='[company:"${company.id}"]' class="btn btn-default"><g:message code="Estado de Cuenta" default="Estado de Cuenta" /></g:link>
          <br />
          <sec:ifAnyGranted roles="ROLE_INTEGRADO">
          <g:link controller="user" action="authorizer" params='[company:"${company.id}"]'><input class="btn btn-default" value="Crear nuevo Autorizador" /></g:link>
        </sec:ifAnyGranted>
      </g:if>
      <g:else>
      <div class="alert alert-warning" role="alert">
        <b>Aún no está autorizado para realizar operaciones</b>
      </div>
    </g:else>
  </form>

</div>
</div>
</div>
</div>
</sec:ifAnyGranted>
<!-- Cuentas -->
<div class="col-md-6">
  <div class="">
    <!-- BEGIN PORTLET -->
    <div class="portlet portlet-blue">
      <!-- BEGIN PORTLET-HEADING -->
      <div class="portlet-heading">
        <div class="portlet-title">
          <h4>Cuentas Bancarias</h4>
        </div>
        <div class="clearfix"></div>
      </div>
      <!-- END OF PORTLET-HEADING -->

      <!-- BEGIN PANEL-COLLAPSE -->
      <div id="defaultPortlet" class="panel-collapse collapse in">
        <!-- BEGIN PORTLET-BODY -->
        <div class="portlet-body">
          <g:if test="${!company.banksAccounts}">
          <h4><span class="label label-warning">Debe agregar al menos una cuenta</span></h4>
          </g:if>
          <g:render template="bankAccounts" />
        </div>
        <!-- END OF PORTLET BODY -->
      </div>
      <!-- END OF PANEL-COLLAPSE-->
      <div class="portlet-footer"></div>
    </div>
    <!-- END OF PORTLET -->
  </div>

  <!-- DOCUMENTS -->
  <g:if test="${company.status == CompanyStatus.ACCEPTED}">
    <g:render template="documents" />
  </g:if>

  <!-- ADDRESSES -->
  <div class="">
    <!-- BEGIN PORTLET -->
    <div class="portlet portlet-blue">
      <!-- BEGIN PORTLET-HEADING -->
      <div class="portlet-heading">
        <!-- BEGIN PORTLET-TITLE -->
        <div class="portlet-title">
          <h4>Direcciones</h4>
        </div>
        <!-- END OF PORTLET-TITLE -->
        <div class="clearfix"></div>
      </div>
      <!-- END OF PORTLET -->

      <!-- BEGIN PANEL COLLAPSE -->
      <div class="panel-collapse collapse in">
        <div class="portlet-body">
          <g:if test="${!company.addresses}">
            <h4><span class="label label-warning">Debe agregar al menos una dirección</span></h4>
          </g:if>
          <g:render template="addresses" />
        </div>
      </div>
      <!-- END OF PANEL COLLAPSE -->
      <div class="portlet-footer"></div>
    </div>
    <!--END OF PORTLET -->
  </div>

  <!-- Telefonos -->
  <div class="">
    <!-- BEGIN PORTLET -->
    <div class="portlet portlet-blue">
      <div class="portlet-heading">
        <div class="portlet-title">
          <h4>Teléfonos</h4>
        </div>
        <div class="clearfix"></div>
      </div>
      <div id="defaultPortlet" class="panel-collapse collapse in">
        <div class="portlet-body">
          <g:render template="telephones" />
        </div>
      </div>
      <div class="portlet-footer"></div>
    </div>
    <!-- END OF PORTLET -->
  </div>
</div>
</div>
</div>
</body>
</html>
