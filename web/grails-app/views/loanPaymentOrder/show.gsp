<%! import com.modulus.uno.LoanPaymentOrderStatus %>
<%! import com.modulus.uno.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanPaymentOrder.label', default: 'LoanPaymentOrder')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="loanPaymentOrder.show" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanPaymentOrder.show"/></li>
      </ol>
    </div>
    <div id="edit-address" class="content scaffold-edit" role="main">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4><g:message code="loanPaymentOrder.show"/></h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="horizontalFormExample" class="panel-collapse collapse in">
          <div class="portlet-body">
            <g:if test="${flash.message}">
            <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
            <g:render template="show"/>
            <br /><br />

            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE, ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
              <g:if test="${loanPaymentOrder.status == LoanPaymentOrderStatus.CANCELED || loanPaymentOrder.status == LoanPaymentOrderStatus.REJECTED}">
                <div class="alert alert-danger" role="alert">
                  <label class="control-label">Motivo ${message(code:'loanPaymentOrder.rejectReason.'+loanPaymentOrder.status)}:</label>
                  <g:message code="rejectReason.${loanPaymentOrder.rejectReason}" default="${loanPaymentOrder.rejectReason}" />
                  <g:if test="${loanPaymentOrder.rejectComment}">
                    <textarea class="form-control" rows="3" cols="60" readonly>${loanPaymentOrder.rejectComment}</textarea>
                  </g:if>
                </div>
                <br /><br />
              </g:if>
            </sec:ifAnyGranted>

            <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
              <g:if test="${loanPaymentOrder.status == LoanPaymentOrderStatus.CREATED}">
                <g:link controller="loanPaymentOrder" action="requestAuthorization" id="${loanPaymentOrder.id}" class="btn btn-success col-md-4"><i class="fa fa-check"></i> Solicitar Autorización</g:link>
              </g:if>
            </sec:ifAnyGranted>

            <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
              <g:if test="${loanPaymentOrder.status == LoanPaymentOrderStatus.VALIDATE &&
                  !loanPaymentOrder.authorizations?.find{it.user == user}}">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-md-4">
                      <g:link controller="loanPaymentOrder" action="authorizeLoanPaymentOrder" id="${loanPaymentOrder.id}" class="btn btn-success btn-block"><i class="fa fa-check"></i> Autorizar Pago</g:link>
                    </div>
                    <div class="col-md-4">
                      <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar Pago</a>
                    </div>
                  </div>
                  <br /><br />
                  <div class="collapse" id="inputReasonCancellation">
                    <div class="well">
                      <g:form action="cancelLoanPaymentOrder">
                        <div class="form-group">
                          <input type="hidden" name="id" value="${loanPaymentOrder.id}"/>
                          <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de cancelación...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                          <g:textArea name="rejectComment" placeholder="Ingrese un comentario de la cancelación" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <button class="btn btn-danger">Ejecutar Cancelación</button>
                        </div>
                      </g:form>
                    </div>
                  </div>
                </div>
              </g:if>
              <g:else>
                <g:if test="${loanPaymentOrder.authorizations?.find{it.user == user}}">
                  <div class="alert alert-warning"><g:message code="order.already.authorized"/></div>
                </g:if>
              </g:else>

            </sec:ifAnyGranted>

            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
              <g:if test="${loanPaymentOrder.status == LoanPaymentOrderStatus.AUTHORIZED}">
                <div class="container-fluid">
                  <div class="row">
                    <div class="col-md-4">
                      <g:link controller="loanPaymentOrder" action="executeLoanPaymentOrder" id="${loanPaymentOrder.id}" class="btn btn-success btn-block"><i class="fa fa-check"></i> Ejecutar Pago</g:link>
                    </div>
                    <div class="col-md-4">
                      <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Rechazar Pago</a>
                    </div>
                  </div>
                  <br /><br />
                  <div class="collapse" id="inputReasonCancellation">
                    <div class="well">
                      <g:form action="rejectLoanPaymentOrder">
                        <div class="form-group">
                          <input type="hidden" name="id" value="${loanPaymentOrder.id}"/>
                          <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de rechazo...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                          <g:textArea name="rejectComment" placeholder="Ingrese un comentario del rechazo" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <button class="btn btn-danger">Ejecutar Rechazo</button>
                        </div>
                      </g:form>
                    </div>
                  </div>
                </div>
              </g:if>
            </sec:ifAnyGranted>

            <br /><br />

            <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
              <g:link action="loanOrderPayments" id="${loanPaymentOrder.loanOrder.id}" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
              <g:link action="listToAuthorize" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
              <g:link action="listAll" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
            </sec:ifAnyGranted>

            <br /><br />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
