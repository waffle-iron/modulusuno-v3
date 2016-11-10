<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanOrder.label', default: 'LoanOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="loanOrder.edit"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanOrder.edit"/></li>
      </ol>
    </div>
    <div class="row">

    <div class="col-lg-12">
      <g:form resource="${this.loanOrder}" method="PUT">
        <g:hiddenField name="version" value="${this.loanOrder?.version}" />
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Datos del préstamo</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
           <g:hasErrors bean="${this.loanOrder}">
            <ul class="errors" role="alert">
              <g:eachError bean="${this.loanOrder}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
              </g:eachError>
            </ul>
           </g:hasErrors>
           <g:render template="form"/>
          </div>
        </div>
        <div class="portlet-footer">
          <input class="btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </div>
      </div>
      </g:form>
    </div>

    </div>
  </body>
</html>
