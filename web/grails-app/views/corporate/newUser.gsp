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
        Agregar Usuario Corporativo
        <small>
          <g:message code="corporate.user.new" />
        </small>
      </h1>
      <ol class="breadcrumb">
        <li>
          <i class="fa fa-caret-square-o-up"></i>
          Usuario
        </li>
      </ol>
    </div>
    <!--END OF PAGE TITLE -->

    <!-- BEGIN CONTAINER -->
    <div class="container">
      <div class=row">
        <div class="col-lg-12 col-md-12">
          <!-- BEGIN PORTLET -->
          <div class="portlet portlet-blue">
            <!-- BEGIN PORTLET-HEADING -->
            <div class="portlet-heading">
              <div class="clearfix"></div>
            </div>
            <!-- END OF PORTLET-HEADING -->
            <!-- BEGIN PORTLET-BODY -->
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${user}">
                <ul class="errors">
                  <g:eachError bean="${user}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
                </ul>
              </g:hasErrors>
              <g:form action="save">
                <fieldset>
                  <g:render template="/user/form" bean="${user}" />
                </fieldset>
              </g:form>
            </div>
            <!-- END OF PORTLET-BODY -->
          </div>
          <!-- END OF PORTLET -->
        </div>
      </div>
    </div>
    <!-- END OF CONTAINER -->
  </body>
</html>
