<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title></title>
  </head>
  <body>
    <!-- BEGIN PAGE TITLE -->
    <div class="page-title">
      <h1>
        <i class="fa fa-plus-circle fa-3x"></i>
        Corporaciones
        <small>
          <g:message code="corporation.new" />
        </small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia </li>
        <li class="active">Creación de Compañia</li>
      </ol>
    </div>

    <sec:ifAnyGranted roles="ROLE_CORPORATIVE">
    <h1>ROLE Corporativo</h1>
    </sec:ifAnyGranted>

    <sec:ifAnyGranted roles="ROLE_FICO_EJECUTOR">
    <h1>ROLE ROLE_FICO_EJECUTOR</h1>
    </sec:ifAnyGranted>

    <div class="row">
      <div class="col-md-12 col-lg-12">
        <g:form action="saveRolesForUser" method="POST" class="form-horizontal" role="form">
        <g:hiddenField name="username" value="${user.username}" />
        <table class="table table-striped">
          <thead>
            <tr>
              <th></th>
              <g:each status="i" in="${roles}" var="role">
                <th>${role}</th>
              </g:each>
            </tr>
          </thead>
          <tbody>
            <g:each status="b" in="${companies}" var="company">
              <tr>
                <td>${company}</td>
                <g:each status="a" in="${roles}" var="someRole">
                  <td>
                    <g:checkBox name="companies.${company}.${someRole}" value="${false}" />
                  </td>
                </g:each>
              </tr>
            </g:each>
          </tbody>
        </table>
        <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.save.label', default: 'Save')}" />
        </g:form>
      </div>
    </div>
  </body>
</html>