<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cashOutOrder.label', default: 'CashOutOrder')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="cashoutOrder.edit.label"/></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active"><g:message code="cashoutOrder.edit.label"/></li>
        </ol>
      </div>
      <div class="col-lg-12">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <br /><br />
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <div id="edit-cashOutOrder" class="content scaffold-create" role="main">
                <g:if test="${flash.message}">
                  <div class="message" role="status">${flash.message}</div>
                </g:if>
                <g:hasErrors bean="${this.cashOutOrder}">
                  <ul class="errors" role="alert">
                    <g:eachError bean="${this.cashOutOrder}" var="error">
                      <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                  </ul>
                </g:hasErrors>
                <g:form resource="${this.cashOutOrder}" role="form" method="PUT">
                  <g:hiddenField name="version" value="${this.cashOutOrder?.version}" />
                  <input type="hidden" name="id" value="${cashOutOrder.id}" />
                    <fieldset class="form">
                      <f:field bean="cashOutOrder" property="amount" label="${message(code:"cashOutOrder.amount")}*" wrapper="currencyRequired"/>
                      <div class="form-group">
                        <label class="control-label"><g:message code="cashoutOrder.account.label" />*</label>
                        <div>
                          <g:select name="account" from="${banksAccounts.sort{it.banco.name}}" optionKey="id" class="form-control" value="${cashOutOrder.account.id}" />
                        </div>
                      </div>
                    </fieldset>
                    <fieldset class="buttons">
                      <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </fieldset>
                </g:form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
