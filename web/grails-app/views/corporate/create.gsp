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
    <!-- END OF PAGE TITLE -->
    <!-- BEGIN PORLET -->
    <div class="portlet portlet-blue">
      <div class="portlet-heading">
        <div class="portlet-title">
          <br/>
          <br/>
        </div>

        <!-- BEGIN PORTLET-WIDGETS -->
        <div class="portlet-widgets">
        </div>
        <!-- END OF PORTLET-WIDGETS -->
        <div class="clearfix"></div>
      </div>

      <!-- BEGIN BLUE-PORTLET -->
      <div id="bluePortlet" class="panel-collapse collapse in">
        <!-- BEGIN PORTLET-BODY -->
        <div class="portlet-body">
          <!-- BEGIN CREATE-ADDRESS -->
          <div id="create-address" class="content scaffold-create" role="main">
            <g:if test="${flash.message}">
              <div class="messasge" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${corporate}">
              <ul class="errors alert alert-danger alert-dismissable" role="alert">
                <g:eachError bean="${corporate}">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
            </g:hasErrors>
            <g:form action="save">
              <fieldset class="form">
                <g:render template="form" bean="${corporate}" />
              </fieldset>
              <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
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