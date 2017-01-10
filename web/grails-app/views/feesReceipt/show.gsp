<%! import com.modulus.uno.FeesReceiptStatus %>
<%! import com.modulus.uno.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'feesReceipt.label', default: 'FeesReceipt')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="feesReceipt.show.label" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active"><g:message code="feesReceipt.active.label" /></b></li>
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

        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
            <g:if test="${flash.message}">
              <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
          <form class="form-horizontal" role="form">
            <br/>
            <f:display bean="feesReceipt" property="id"  wrapper="describe"/>
            <f:display bean="feesReceipt" property="collaboratorName"  wrapper="describe"/>
            <f:display bean="feesReceipt" property="amount"  wrapper="describePrice"/>
            <f:display bean="feesReceipt" property="iva"  wrapper="describePrice"/>
            <f:display bean="feesReceipt" property="isr"  wrapper="describeReceiptPrice"/>
            <f:display bean="feesReceipt" property="ivaWithHolding"  wrapper="describeReceiptPrice"/>
            <div class="form-group">
              <label class="col-sm-5 control-label"><g:message code="feesReceipt.total" /></label>
              <div class="col-sm-4">
                <p class="text-success"><strong>$ ${feesReceipt.amount + feesReceipt.iva - feesReceipt.isr - feesReceipt.ivaWithHolding}</strong></p>
            </div>
            </div>
            <f:display bean="feesReceipt" property="status"  wrapper="describe"/>
            <g:if test="${feesReceipt.status == FeesReceiptStatus.CANCELADA || feesReceipt.status == FeesReceiptStatus.RECHAZADA }">
              <f:display bean="feesReceipt" property="rejectReason"  wrapper="describe"/>
            </g:if>

          </form>
          <g:if test="${feesReceipt.documents}">
          <h4>Documentos adjuntos</h4>
          <ul>
            <g:each in="${feesReceipt.documents}" var="document">
            <li>
              <a href="${baseUrlDocuments}/${document.title}.${document.mimeType}"><i class="glyphicon glyphicon-download-alt"></i> ${document}</a>
            </li>
            </g:each>
          </ul>
          </g:if>

          <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR, ROLE_OPERATOR_EJECUTOR">

          <g:if test="${feesReceipt.status == FeesReceiptStatus.CREADA}">
          <hr/>
          <div class="row">
            <div class="col-md-4">
              <h3><g:message code="feesReceipt.documentos" /></h3>
              <h4><g:message code="feesReceipt.documentos.typos" /></h4>
          <g:form class="form-horizontal" action="addDocumentToFeesReceipt" name="documentForFeesReceipt" method="POST" enctype="multipart/form-data" id="${feesReceipt.id}">
            <g:if test="${!feesReceipt.documents}">
              <input type="file" required="" class="form-control" name="feesReceiptDocument" accept="application/pdf" maxlength="5000000" />
            </g:if>
            <g:else>
              <g:if test="${feesReceipt.documents?.size()==1}">
                <input type="file" required="" class="form-control" name="feesReceiptDocument" accept="text/xml" maxlength="5000000" />
              </g:if>
              <g:else>
                <input type="file" required="" class="form-control" name="feesReceiptDocument" accept="application/pdf,image/*" maxlength="5000000" />
              </g:else>
            </g:else>
            <br/>
            <input type="submit" class="btn btn-default" value="Subir comprobante de recibo de honorarios" />
            <br/><br/>
          </g:form>
            </div>
          </div>
          </g:if>

          <div class="row">
            <div class="col-md-4">
              <g:if test="${feesReceipt.status == FeesReceiptStatus.CREADA && feesReceipt.documents.size() > 1}">
                <g:link action="sendToAuthorize" class="btn btn-success btn-block" id="${feesReceipt.id}">Confirmar el Recibo de Honorarios</g:link>
              </g:if>
            </div>
          </div>

          </sec:ifAnyGranted>

          <fieldset class="buttons">
            <g:if test="${feesReceipt.status == FeesReceiptStatus.POR_AUTORIZAR}">
            <sec:ifAnyGranted roles="ROLE_AUTHORIZER_EJECUTOR">
            <div class="container-fluid">
              <div class="row">
                <div class="col-md-6">
                  <g:link action="authorizeFeesReceipt" class="btn btn-warning btn-block" id="${feesReceipt.id}">Autorizar Recibo de Honorarios</g:link>
                </div>
                <div class="col-md-6">
                  <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar el Recibo de Honorarios</a>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <div class="collapse" id="inputReasonCancellation">
                      <div class="well">
                        <g:form action="cancelFeesReceipt" id="${feesReceipt.id}">
                        <div class="form-group">
                          <g:select name="rejectReason" from="${RejectReason.values()}" value="${feesReceipt.rejectReason}" class="form-control" />
                          <br/>
                          <g:textArea name="comments" placeholder="Comentarios opcionales" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <br/>
                          <button type="submit" class="btn btn-danger">Ejecutar Cancelación</button>
                        </div>
                          </g:form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            </sec:ifAnyGranted>
            </g:if>
          </fieldset>
          <fieldset class="buttons">
            <g:if test="${feesReceipt.status == FeesReceiptStatus.AUTORIZADA}">
            <sec:ifAnyGranted roles="ROLE_FICO_EJECUTOR">
            <div class="container-fluid">
              <div class="row">
                <div class="col-md-6">
                  <g:link action="executeFeesReceipt" class="btn btn-success btn-block" id="${feesReceipt.id}">Ejecutar Recibo de Honorarios</g:link>
                </div>
                <div class="col-md-6">
                  <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Rechazar el Recibo de Honorarios</a>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <div class="collapse" id="inputReasonCancellation">
                      <div class="well">
                        <g:form action="rejectFeesReceipt" id="${feesReceipt.id}">
                        <div class="form-group">
                          <g:select name="rejectReason" from="${RejectReason.values()}" value="${feesReceipt.rejectReason}" class="form-control" />
                          <br/>
                          <g:textArea name="comments" placeholder="Comentarios opcionales" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <br/>
                          <button type="submit" class="btn btn-danger">Rechazar Recibo de Honorarios</button>
                        </div>
                          </g:form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            </sec:ifAnyGranted>
            </g:if>
          </fieldset>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
