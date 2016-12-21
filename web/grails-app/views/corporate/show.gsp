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
          <small>Información de la Corporación</small>
        </h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Corporación</li>
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
              <h4>Datos de la Corporación</h4>
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
          <div class="portlet-footer">
            <div class="text-right">
              <g:link class="btn btn-default" >
                <g:message code="default.button.edit.label" default="Edit" />
              </g:link>
            </div>
          </div>
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

          </div>
          <!-- END OF PANEL -->

        </div>
        <!-- END OF PORTLET-DEFAULT -->
      </div>
    </div>
    <!-- END OF ROW -->

  </body>
</html>
