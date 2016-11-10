<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanPaymentOrder.label', default: 'LoanPaymentOrder')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>
      <i class="icon-pago fa-3x"></i>
      Operaciones | Pagos a préstamos
      <g:message code="loanPaymentOrder.create"/>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanPaymentOrder.create"/></li>
      </ol>
    </div>
    <div class="row">

    <div class="col-lg-12">
      <g:form controller="loanPaymentOrder" action="save">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Datos del pago a préstamo</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
           <g:hasErrors bean="${this.loanPaymentOrder}">
            <ul class="errors" role="alert">
              <g:eachError bean="${this.loanPaymentOrder}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
              </g:eachError>
            </ul>
           </g:hasErrors>
           <g:render template="form"/>
          </div>
        </div>
        <div class="portlet-footer">
          <button type="submit" class="btn btn-default">
            Crear
          </button>
        </div>
      </div>
      </g:form>
    </div>

    </div>
    <asset:javascript src="loanPaymentOrder/create.js"/>
  </body>
</html>
