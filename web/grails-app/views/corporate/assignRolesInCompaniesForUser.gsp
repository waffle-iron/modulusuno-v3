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
        <i class="fa fa-user fa-3x"></i>
          ${user.profile.getFullName()}
        <small>
          ${user.profile.email}
        </small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia </li>
        <li class="active">Creación de Compañia</li>
      </ol>
    </div>

    <div class="row">
      <div class="col-md-12 col-lg-12">
        <g:form action="saveRolesForUser" method="POST" class="form-horizontal" role="form">
        <g:hiddenField name="username" value="${user.username}" />
        <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th></th>
              <g:each status="i" in="${roles}" var="role">
                <th><g:message code="${role.authority.toLowerCase()}" /></th>
              </g:each>
            </tr>
          </thead>
          <tbody>
            <g:each status="b" in="${companies}" var="company">
              <tr>
                <td>${company}</td>
                <g:each status="a" in="${roles}" var="someRole">
                  <td>
                    <modulusuno:checkboxForRoleAtCompany company="${company}" role="${someRole}" rolesOfUser="${rolesOfUser}" />
                  </td>
                </g:each>
              </tr>
            </g:each>
          </tbody>
        </table>
        </div>
        <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.save.label', default: 'Save')}" />
        </g:form>
      </div>
    </div>
  </body>
</html>
