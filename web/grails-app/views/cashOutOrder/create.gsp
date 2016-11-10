<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cashOutOrder.label', default: 'CashOutOrder')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1>
         <i class="icon-retiro fa-3x"></i>
         Operaciones / Retiro
         <small><g:message code="cashoutOrder.create.label" /></small>
       </h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active"><g:message code="cashoutOrder.create.label"/></li>
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
              <div id="create-cashOutOrder" class="content scaffold-create" role="main">
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
                  <g:form action="save">
                      <fieldset class="form">
                        <input type="hidden" value="${company.id}" name="company" />
                        <input type="hidden" value="${company.accounts.first().timoneUuid}" name="timoneUuid" />
                        <input type="hidden" value="${company.accounts.first().account}" name="timoneAccount" />
                        <f:field bean="cashOutOrder" property="amount" label="${message(code:"cashOutOrder.amount")}*" wrapper="currencyRequired"/>
                        <div class="form-group">
                          <label class="control-label"><g:message code="cashoutOrder.account.label" />*</label>
                          <div>
                            <g:select name="account" from="${company.banksAccounts.sort{it.banco.name}}"  optionKey="id" class="form-control" />
                          </div>
                        </div>
                      </fieldset>
                      <fieldset class="buttons">
                          <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                      </fieldset>
                  </g:form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
