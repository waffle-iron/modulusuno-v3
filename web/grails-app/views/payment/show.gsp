<%! import com.modulus.uno.PaymentStatus %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'payment.label', default: 'Payment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="payment.show" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
          <li class="active">Notificación de Pago a Integrado</li>
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
              <ol class="property-list payment">
                <li class="form-group">
                  <label class="control-label">Compañía</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    ${payment.company}
                  </div>
                </li>
                <li class="form-group">
                  <label class="control-label">Monto</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    ${integradora.formatPrice(number:payment.amount)}
                  </div>
                </li>
                <li class="form-group">
                  <label class="control-label">Estatus</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    <g:message code="payment.${payment.status}"/>
                  </div>
                </li>

                <g:if test="${payment.status == PaymentStatus.CONCILIATED}">
                  <li class="form-group">
                    <label class="control-label">Factura</label>
                    <div class="form-control" aria-labelledby="${label}-label">
                      <g:link controller="saleOrder" action="show" id="${payment.saleOrder.id}">
                        ${payment.saleOrder.clientName} - ${integradora.formatPrice(number:payment.saleOrder.total)}
                      </g:link>
                    </div>
                  </li>
                </g:if>

              </ol>

              <g:if test="${params.status}">
                <g:link action="index" params="[status:"${params.status}"]" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
              </g:if>
              <g:else>
                <g:link action="index" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
              </g:else>

              <g:if test="${payment.status == PaymentStatus.PENDING}">
              <g:link action="cancelPayment" id="${payment.id}"  class="list btn btn-default">Cancelar</g:link>
              </g:if>

            </div>
          </div>
        </div>
      </div>
   </body>
</html>
