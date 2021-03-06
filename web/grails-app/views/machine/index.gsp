<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta name="layout" content="main" />
    <title></title>
  </head>
  <body>
    <!-- BEGIN PAGE TITLE -->
    <div class="page-title">
      <h1>
        <i class="fa fa-cog fa-3x"></i>
        <g:message code="machine.index" args="[entityName]" />
        <small>
          <g:message code="machine.list" />
        </small>
      </h1>
    </div>
    <!-- END OF PAGE TITLE -->
    <!-- BEGIN PORTLET -->
    <div class="portlet portlet-blue">
      <!-- BEGIN BLUE-PORTLET -->
      <div class="panel-collapse collapse in">
        <!-- BEGIN PORTLET-BODY -->
        <div class="portlet-body">
          <!-- BEGIN CREATE-ADDRESS -->
          <div class="content scaffold-create">
            <form action="create" method="POST">
              <fieldset class="form">
                <div class="row">
                  <div class="form-group col-lg-3">
                    <label for="entity">
                      ${message(code:'machine.for.label')}
                    </label>
                  </div>
                  <div class="form-group col-lg-3">
                    <label for="entity">
                      ${message(code:'company.label')}
                    </label>
                  </div>
                </div>
                <div class="row">
                  <div class="form-group col-lg-3">
                    <g:select name="entity" class="form-control" from="${entities}" optionKey="key" optionValue="value" noSelection="${['':'Seleccionar']}"></g:select>
                  </div>

                  <div class="form-group col-lg-3">
                    <g:select name="company" class="form-control" from="${companies}" optionKey="id" optionValue="bussinessName" noSelection="${['':'Seleccionar']}"></g:select>
                  </div>

                  <div class="form-group col-lg-3">
                    <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                  </div>
                </div>
              </fieldset>
            </form>
          </div>
          <!-- END OF CREATE-ADDRESS -->
        </div>
        <!-- END OF PORTLET-BODY -->
      </div>
      <!-- END OF BLUE PORTLET -->
    </div>
    <!-- END OF PORTLET -->
  </body>
</html>
