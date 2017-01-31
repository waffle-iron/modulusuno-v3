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
        Corporativo
        <small>
          <g:message code="corporate.new" />
        </small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia </li>
        <li class="active">Creación de Compañia</li>
      </ol>
    </div>
    <!-- END OF PAGE TITLE -->
    <!-- BEGIN PORLET -->
    <div class="portlet portlet-blue">
      <div class="portlet-heading">
        <!-- BEGIN PORTLET-WIDGETS -->
        <div class="portlet-widgets">
        </div>
        <!-- END OF PORTLET-WIDGETS -->
        <div class="clearfix"></div>
      </div>

      <!-- BEGIN BLUE-PORTLET -->
      <div class="panel-collapse collapse in">
        <!-- BEGIN PORTLET-BODY -->
        <div class="portlet-body">
          <!-- BEGIN CREATE-ADDRESS -->
          <div class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
              <div class="messasge" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${corporate}">
              <ul class="errors alert alert-danger alert-dismissable" role="alert">
                <g:eachError bean="${corporate}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
            </g:hasErrors>
            <g:if test="${flash.error}">
            <div class="errors alert alert-danger alert-dismissable">
              ${flash.error}
            </div>
            </g:if>
            <g:form action="save">
              <fieldset class="form">
                <g:render template="form" bean="${corporate}" />
                <div class="col-sm-12">
                  <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </div>
              </fieldset>
            </g:form>
          </div>
          <!-- END OF CREATE ADDRESS -->
        </div>
        <!-- END OF PORTLET-BODY -->
      </div>
      <!-- END OF BLUE-PORTLET -->
    </div>
    <!-- END OF PORTLET  -->
  </body>
</html>
