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

    <div class="row">
      <div class="col-md-12 col-lg-12">
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
                    <g:checkBox name="myCheckbox" value="${false}" />
                  </td>
                </g:each>
              </tr>
            </g:each>
          </tbody>
        </table>
      </div>
    </div>
  </body>
</html>