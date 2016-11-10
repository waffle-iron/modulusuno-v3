<%! import com.modulus.uno.CompanyStatus %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'commission.label', default: 'Commission')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
      <i class="fa fa-info-circle fa-3x"></i>
      Comisiones
      <small><g:message code="commission.list.label" /></small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Comisiones</li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4><g:message code="commission.list.label"/></h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
            <div class="portlet-body">


      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <div class="table-responsive">
        <table class="table">
        <tr>
          <th><g:message code="commission.fee.label"/></th>
          <th><g:message code="commission.percentage.label"/></th>
          <th><g:message code="commission.type.label"/></th>
          <th><g:message code="commission.company"/></th>
        </tr>
        <g:each in="${commissionList}" var="commission">
        <tr>
          <td>
          <g:link action="show" id="${commission.id}">
            ${integradora.formatPrice(number:commission.fee)}
          </g:link>
          </td>
          <td>${commission.percentage}</td>
          <td>${commission.type}</td>
          <td>
          <g:link controller="company" action="show" id="${commission.company.id}">
            ${commission.company}
          </g:link>
          </td>
        </tr>
        </g:each>
        </table>
      </div>
      <div class="pagination">
        <g:paginate total="${commissionCount ?: 0}" />
      </div>
    <g:link controller="company" action="show" id="${session?.company ?: company?.id}" class="home btn btn-default">
      <g:message code="company.show"/>
    </g:link>
    <g:if test="${commissionList?.size() < 3}">
      <g:link class="create btn btn-default" action="create" params="[companyId:params.companyId?:company.id]">
        <g:message code="commission.create.label" args="[entityName]" />
      </g:link>
    </g:if>
    <sec:ifAnyGranted roles="ROLE_ADMIN">
    <g:if test="${company.status == CompanyStatus.VALIDATE}">
    <g:link controller="managerApplication" action="accepted" class="btn btn-success" params="[companyId:company.id]">
      Aprobar Solicitud
    </g:link>
    </g:if>
    </sec:ifAnyGranted>

            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
