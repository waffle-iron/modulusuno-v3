<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
  </head>
  <body>
    <!-- BEGIN ROW -->
    <div class="row">
      <!-- BEGIN PAGE-TITLE -->
      <div class="page-title">
        <h1>
          <i class="fa fa-info-circle fa-3x"></i>
          <g:message code="corporate.show" />
          <small>Información del Corporativo</small>
        </h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Corporativo</li>
          <li class="active">
            <g:message code="corporate.show" />
          </li>
        </ol>
      </div>
      <!-- END OF PAGE TITLE -->
    </div>
    <!-- END OF ROW -->

    <!-- BEGIN ROW -->
    <div class="row">
      <div class="col-lg-6 col-md-6">
        <!-- BEGIN PORTLET-DEFAULT -->
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos del Corporativo</h4>
            </div>
            <div class="clearfix"></div>
          </div>

          <!-- BEGIN DEFAULT PORTLET -->
          <div class="panel-collapse collapse in">
            <!-- BEGIN PORTLET-BODY -->
            <div class="portlet-body">
              <form class="form-horizontal" role="form">
                <div class="form-group">
                  <label class="col-sm-12 control-label">
                    <g:message code="corporate.nameCorporate" />
                  </label>
                  <div class="col-lg-12 col-sm-12 col-xs-12">
                    <g:message code="${corporate.nameCorporate}" />
                  </div>
                </div>
                <f:display bean="corporate" property="corporateUrl" wrapper="describe" />
              </form>
            </div>
            <!-- END OF PORTLET-BODY -->
          </div>
          <!-- END OF DEFAULT PORTLET -->
          <!-- BEGIN PORTLET-FOOTER -->

          <!-- TODO: Edit the corporate -
          <div class="portlet-footer">
            <div class="text-right">
              <g:link class="btn btn-default" >
                <g:message code="default.button.edit.label" default="Edit" />
              </g:link>
            </div>
          </div>
          -->

          <!-- END OF PORTLET FOOTER -->
        </div>
        <!-- END OF PORTLET DEFAULT -->
      </div>

      <div class="col-md-6 col-lg-6">
        <!-- BEGIN PORTLET-DEFAULT -->
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Usuarios</h4>
            </div>
            <div class="clearfix"></div>
          </div>

          <!-- BEGIN PANEL-->
          <div class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${users}">
                <table class="table">
                  <g:each var="user" in="${users}">
                    <tr>
                      <td class="text-primary">
                        ${user.profile.fullName}
                      </td>
                    </tr>
                  </g:each>
                </table>
                <div class="text-right">
                  <g:link action="addUser" controller="corporate" class="btn btn-default" id="${corporate.id}"><i class="fa fa-plus"></i></g:link>
                </div>
              </g:if>
              <g:else>
                <div class="alert alert-info">
                  <p>No hay usuarios registrados.</p>
                </div>
                <p>
                  <g:link class="btn btn-default btn-lg" controller="corporate" action="addUser" id="${corporate.id}">
                    Registrar un usuario
                  </g:link>
                </p>
              </g:else>
            </div>
          </div>
          <!-- END OF PANEL -->
        </div>
        <!-- END OF PORTLET-DEFAULT -->
      </div>

  </body>
</html>
