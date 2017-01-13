<%! import com.modulus.uno.LoanOrderStatus %>
<%! import com.modulus.uno.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanOrder.label', default: 'Orden de Préstamo')}" />
    <title><g:message code="loanOrder.show" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="loanOrder.show" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active"><g:message code="loanOrder.show"/></li>
      </ol>
    </div>
    <div id="edit-address" class="content scaffold-edit" role="main">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <br />
            <br />
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="horizontalFormExample" class="panel-collapse collapse in">
          <div class="portlet-body">
            <g:if test="${flash.message}">
            <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
            <ol class="property-list loanOrder">
              <li class="form-group">
                <label class="control-label">No. de Orden</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${loanOrder.id}
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Compañía</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${loanOrder.company}
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Monto</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${modulusuno.formatPrice(number:loanOrder.amount)}
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Plazo (meses)</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${loanOrder.term}
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Tasa Anual</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${loanOrder.rate}
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Estatus</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  <g:message code="${loanOrder.status.code}"/>
                </div>
              </li>
              <li class="form-group">
                <label class="control-label">Deudor</label>
                <div class="form-control" aria-labelledby="${label}-label">
                  ${loanOrder.creditor}
                </div>
              </li>
            </ol>
            <g:if test="${loanOrder.documents}">
              <h4>Documentos adjuntos</h4>
                <ul>
                  <g:each in="${loanOrder.documents}" var="document">
                  <li>
                  <a href="${baseUrlDocuments}/${document.title}.${document.mimeType}"><i class="glyphicon glyphicon-download-alt"></i> ${document}</a>
                  </li>
                  </g:each>
                </ul>
            </g:if>
            <g:if test="${loanOrder.status == LoanOrderStatus.APPROVED}">
            <hr/>
            <h4>Agregar imágen documento firmado</h4>
            <g:form class="form-horizontal" action="addDocumentToLoanOrder" name="documentForFeesReceipt" method="POST" enctype="multipart/form-data" id="${loanOrder.id}">
            <input type="file" required="" class="form-control" name="loanOrderDocument" accept="image/*" maxlength="5000000" />
            <br/>
            <input type="submit" class="btn btn-default" value="Subir documento" />
            </g:form>
            <g:if test="${loanOrder.documents}">
              <br/>
              <g:link action="acceptLoanOrder" class="btn btn-success" id="${loanOrder.id}">Aceptar el préstamo</g:link>
            </g:if>
            </g:if>
            <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR,ROLE_LEGAL_REPRESENTATIVE_VISOR,ROLE_OPERATOR_VISOR">
              <g:if test="${loanOrder.status == LoanOrderStatus.CANCELED || loanOrder.status == LoanOrderStatus.REJECTED}">
                <div class="alert alert-danger" role="alert">
                  <label class="control-label">Motivo ${message(code:'loanOrder.rejectReason.'+loanOrder.status)}:</label>
                  <g:message code="rejectReason.${loanOrder.rejectReason}" default="${loanOrder.rejectReason}" />
                  <g:if test="${loanOrder.rejectComment}">
                  <textarea class="form-control" rows="3" cols="60" readonly>${loanOrder.rejectComment}</textarea>
                  </g:if>
                </div>
              </g:if>
            </sec:ifAnyGranted>

            <sec:ifAnyGranted roles="ROLE_AUTHORIZER_EJECUTOR">
              <g:if test="${loanOrder.status == LoanOrderStatus.VALIDATE && !loanOrder.authorizations?.find{it.user == user}}">
                <g:link action="authorizeLoanOrder" id="${loanOrder.id}" class="btn btn-warning"> Autorizar Préstamo</g:link>
                <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar Préstamo</a>
                <br /><br />
                <div class="collapse" id="inputReasonCancellation">
                  <div class="well">
                    <g:form action="cancelLoanOrder">
                      <div class="form-group">
                        <input type="hidden" name="id" value="${loanOrder.id}"/>
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de cancelación...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario de la cancelación" rows="3" cols="60" maxLength="255" class="form-control"/>
                        <button class="btn btn-danger">Ejecutar Cancelación</button>
                      </div>
                    </g:form>
                  </div>
                </div>
              </g:if>
              <g:else>
                <g:if test="${loanOrder.authorizations?.find{it.user == user}}">
                  <div class="alert alert-warning"><g:message code="order.already.authorized"/></div>
                </g:if>
              </g:else>
            </sec:ifAnyGranted>

            <sec:ifAnyGranted roles="ROLE_FICO_EJECUTOR">
              <g:if test="${loanOrder.status == LoanOrderStatus.AUTHORIZED}">
                <g:link action="approveLoanOrder" id="${loanOrder.id}" class="btn btn-primary">Aprobar Préstamo</g:link>
                <a data-toggle="collapse" role="button" href="#inputReasonRejected" class="btn btn-danger" aria-expanded="false" aria-controls="inputReasonRejected">Rechazar Préstamo</a>
                <br /><br />
                <div class="collapse" id="inputReasonRejected">
                  <div class="well">
                    <g:form action="rejectLoanOrder">
                      <div class="form-group">
                        <input type="hidden" name="id" value="${loanOrder.id}"/>
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de rechazo...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario del rechazo" rows="3" cols="60" maxLength="255" class="form-control"/>
                        <button class="btn btn-danger">Ejecutar Rechazo</button>
                      </div>
                    </g:form>
                  </div>
                </div>
              </g:if>
              <g:if test="${loanOrder.status == LoanOrderStatus.ACCEPTED}">
                <g:link action="executeLoanOrder" id="${loanOrder.id}" class="btn btn-success">Ejecutar Préstamo</g:link>
              </g:if>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR">
              <g:if test="${loanOrder.status == LoanOrderStatus.CREATED && loanOrder.creditor}">
                <g:link controller="loanOrder" action="confirmLoanOrder" id="${loanOrder.id}" class="btn btn-success"><i class="fa fa-check"></i> Solicitar Autorización</g:link>
              </g:if>
              <g:if test="${loanOrder.status == LoanOrderStatus.CREATED}">
                <g:link action="edit" id="${loanOrder.id}" class="btn btn-default">Editar</g:link>
              </g:if>
            </sec:ifAnyGranted>
            <br /><br />
            <sec:ifAnyGranted roles="ROLE_FICO_VISOR,ROLE_FICO_EJECUTOR">
              <g:link action="index" class="list btn btn-default"><i class="fa fa-reply"></i><g:message code="default.back" /></g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR">
              <g:link action="list" class="list btn btn-default"><i class="fa fa-reply"></i><g:message code="default.back" /></g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_AUTHORIZER_VISOR, ROLE_AUTHORIZER_EJECUTOR">
              <g:link action="listToAuthorize" class="list btn btn-default"><i class="fa fa-reply"></i><g:message code="default.back" /></g:link>
            </sec:ifAnyGranted>
            <br /><br />
          </div>
        </div>
      </div>
  </body>
</html>
