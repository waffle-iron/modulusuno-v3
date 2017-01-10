<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title><g:message code="manager.list.label" /></title>
  </head>
  <body>
    <!-- BEGIN PAGE-TITLE -->
    <div class="page-title">
      <h1>
      <i class="fa fa-warning fa-3x"></i>
      Modulus UNO | Empresas
      <small><g:message code="corporate.companies.list" /></small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Administracion</li>
        <li class="active">Lista de empresas</li>
      </ol>
    </div>

    <!-- BEGIN TAB-CONTENT -->
    <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="acepted">
        <div id="list-company" class="col-lg-12" role="main">
          <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
          </g:if>
          <g:render template="/corporate/companyList" model="[companies:companiesAccepted]"/>
          </div>
        </div>
      </div>
    </div>
    <!-- END OF TAB-CONTENT -->
  </body>
</html>
