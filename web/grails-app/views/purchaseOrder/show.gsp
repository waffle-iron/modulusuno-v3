<%! import com.modulus.uno.PurchaseOrderStatus %>
<%! import com.modulus.uno.UnitType %>
<%! import com.modulus.uno.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'purchaseOrder.label', default: 'PurchaseOrder')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <g:if test="${purchaseOrder.isMoneyBackOrder}">
      <g:set var="messageOrder" value="Orden de Reembolso"/>
      <g:set var="messageBusinessEntityOrder" value="Empleado"/>
      <g:set var="messageDocuments" value="PDF, XML, IMAGEN(PNG,JPG)"/>
    </g:if><g:else>
      <g:set var="messageOrder" value="Orden de Pago a Proveedor"/>
      <g:set var="messageBusinessEntityOrder" value="Proveedor"/>
      <g:set var="messageDocuments" value="PDF y XML"/>
    </g:else>

    <div class="page-title">
      <h1>Detalle de la ${messageOrder}</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active">Detalle de la ${messageOrder} </li>
      </ol>
    </div>


    <div class="row">
      <div class="col-lg-6">
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos del ${messageBusinessEntityOrder}</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <dl class="dl-horizontal">
                <dt>Nombre:</dt>
                <dd>${purchaseOrder.providerName}</dd>
                <dt>Cuenta Bancaria:</dt>
                <dd>${purchaseOrder.bankAccount}</dd>
                <dt>Fecha de creación:</dt>
                <dd>${formatDate(date:purchaseOrder.dateCreated, format:'dd-MMMM-yyyy HH:mm')}</dd>
                <dt>Fecha de Pago:</dt>
                <dd>${formatDate(date:purchaseOrder.fechaPago?:purchaseOrder.dateCreated, format:'dd-MMMM-yyyy')}</dd>
                <g:if test="${purchaseOrder.isAnticipated}">
                  <p>
                  <div class="alert alert-info" style="text-align:center" >
                    Orden de Pago a Proveedor Anticipada
                  </div>
                </g:if>
              </dl>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos de la orden</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
           <div class="portlet-body">
              <dl class="dl-horizontal">
                <dt>No. de Orden</dt>
                <dd>
                ${purchaseOrder.id}
                </dd>
                <dt>Compañía</dt>
                <dd>
                ${purchaseOrder.company}
                </dd>
                <dt>Cantidad de detalles</dt>
                <dd>
                ${purchaseOrder.items.size()}
                </dd>
                <dt>Subtotal</dt>
                <dd>
                ${modulusuno.formatPrice(number:purchaseOrder.subtotal)}
                </dd>
                <dt>IVA</dt>
                <dd>${modulusuno.formatPrice(number:purchaseOrder.totalIVA)}</dd>
                <dt>IEPS</dt>
                <dd>${modulusuno.formatPrice(number:purchaseOrder.totalIEPS)}</dd>
                <dt>Total</dt>
                <dd>${modulusuno.formatPrice(number:purchaseOrder.total)}</dd>
                <dt>Estado </dt>
                <dd><g:message code="purchaseOrder.status.${purchaseOrder.status}" default="${purchaseOrder.status}"/></dd>
              </dl>
             <p>
              <g:if test="${purchaseOrder.status == PurchaseOrderStatus.CANCELADA || purchaseOrder.status == PurchaseOrderStatus.RECHAZADA}">
                <div class="alert alert-danger" role="alert">
                <label class="control-label">Motivo ${message(code:'purchaseOrder.rejectReason.'+purchaseOrder.status)}:</label>
                <g:message code="rejectReason.${purchaseOrder.rejectReason}" default="${purchaseOrder.rejectReason}"/>
                <textarea class="form-control" rows="3" cols="60" readonly>${purchaseOrder.rejectComment}</textarea>
                </div>
              </g:if>
            </p>
            </div>
          </div>
        </div>
      </div>
    </div>


      <g:if test="${purchaseOrder.items}">
        <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
          <g:if test="${(purchaseOrder.status == PurchaseOrderStatus.CREADA && (purchaseOrder.documents && ((!purchaseOrder.isMoneyBackOrder && purchaseOrder.documents?.size()>=2) || (purchaseOrder.isMoneyBackOrder && purchaseOrder.documents.size()==3) ))) || (purchaseOrder.status == PurchaseOrderStatus.CREADA && purchaseOrder.isAnticipated)}">
            <div class="row">
              <div class="col-md-4 col-md-offset-8">
                <g:form controller="purchaseOrder" action="sendOrderToConfirmation" id="${purchaseOrder.id}">
                  <button type="submit" class="btn btn-success btn-block">
                    Solicitar Autorización
                  </button>
                </g:form>
              </div>
            </div>
          </g:if>
        </sec:ifAnyGranted>

        <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
          <g:if test="${purchaseOrder.status == PurchaseOrderStatus.POR_AUTORIZAR &&
             !purchaseOrder.authorizations?.find{it.user == user}}">
            <div class="row">
              <div class="col-md-4 col-md-offset-4">
              <g:if test="${purchaseOrder.bankAccount}">
                <g:form controller="purchaseOrder" action="confirmThePurchaseOrder" id="${purchaseOrder.id}">
                  <button type="submit" class="btn btn-warning btn-block">
                    Autorizar el pago
                  </button>
                </g:form>
              </g:if>
              </div>
              <div class="col-md-4">
                <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar la orden</a>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="collapse" id="inputReasonCancellation">
                  <div class="well">
                    <g:form action="cancelPurchaseOrder" id="${purchaseOrder.id}">
                    <div class="form-group">
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de cancelación...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario de la cancelación" rows="3" cols="60" maxLength="255" class="form-control"/>
                      <button type="submit" class="btn btn-danger">Ejecutar Cancelación</button>
                    </div>
                    </g:form>
                  </div>
                </div>
              </div>
            </div>
          </g:if>
          <g:else>
            <g:if test="${purchaseOrder.authorizations?.find{it.user == user}}">
              <div class="alert alert-warning"><g:message code="order.already.authorized"/></div>
            </g:if>
          </g:else>
        </sec:ifAnyGranted>

        <sec:ifAnyGranted roles="ROLE_ADMIN_IECCE">
          <g:if test="${purchaseOrder.status == PurchaseOrderStatus.AUTORIZADA }">
            <div class="row">
              <div class="col-md-4 col-md-offset-4">
              <g:if test="${purchaseOrder.bankAccount}">
                <g:form controller="purchaseOrder" action="executePurchaseOrder" id="${purchaseOrder.id}">
                  <button type="submit" class="btn btn-primary btn-block">
                    Ejecutar el pago
                  </button>
                </g:form>
              </g:if>
              </div>
              <div class="col-md-4">
                <a data-toggle="collapse" role="button" href="#inputReasonRejected" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonRejected">Rechazar la orden</a>
              </div>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="collapse" id="inputReasonRejected">
                  <div class="well">
                    <g:form action="rejectPurchaseOrder" id="${purchaseOrder.id}">
                    <div class="form-group">
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de rechazo...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario del rechazo" rows="3" cols="60" maxLength="255" class="form-control"/>
                      <button type="submit" class="btn btn-danger">Ejecutar Rechazo</button>
                    </div>
                    </g:form>
                  </div>
                </div>
              </div>
            </div>
          </g:if>
        </sec:ifAnyGranted>
      </g:if>
    <g:if test="${!purchaseOrder.bankAccount}">
      <div class="alert alert-danger">
        La Orden de ${messageOrder} no tiene cuenta bancaria definida
      </div>
    </g:if>
   <p></p>


    <div class="row">
      <g:if test="${flash.message}">
        <div class="alert alert-info">${flash.message}</div>
      </g:if>
      <g:if test="${insufficientBalance}">
        <div class="alert alert-danger" role="alert">${insufficientBalance}</div>
      </g:if>
      <div class="table-responsive">
        <g:form controller="purchaseOrderItem" action="save">
        <table class="table">
          <thead>
            <tr>
              <th>Cantidad</th>
              <th class="col-xs-5">Descripción del producto</th>
              <th class="col-xs-2">Precio Unitario</th>
              <th class="col-xs-3">Unidad de medida</th>
              <th class="col-xs-2">Importe</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <g:if test="${purchaseOrder.status == PurchaseOrderStatus.CREADA}">
            <g:hiddenField name="purchaseOrder.id" value="${purchaseOrder.id}"/>
            <tr>
              <td>
                <div class="input-group">
                  <input id="quantity" name="quantity" class="form-control" type="number"min="1" required="" />
                </div>
              </td>
              <td>
                <div class="input-group">
                    <g:textField id="product-name" name="name" class="form-control" required="" placeholder="Nombre del producto" style="width:300px" maxLength="100"/>
                </div>
             </td>
              <td>
                <div class="input-group">
                  <div class="input-group-addon">$</div>
                  <input type="text" id="price" name="price" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto" style="width:100px;height:35px"/>
                </div>
                <div class="input-group">
                  <div class="input-group-addon">%</div>
                  <input type="text" id="ieps" name="ieps" class="form-control" required="" pattern="[0-9]+(\.[0-9]{2})?" value="0"/>
                  <div class="input-group-addon">IEPS</div>
                </div>
                <div class="input-group">
                  <div class="input-group-addon">%</div>
                  <input type="text" id="iva" name="iva" class="form-control" required="" pattern="[0-9]+(\.[0-9]{2})?" value="16"/>
                  <div class="input-group-addon">IVA</div>
                </div>
                <div class="input-group">
                  <div class="input-group-addon">$</div>
                  <input type="text" id="netprice" name="netprice" class="form-control" value="" readonly=""/>
                </div>
              </td>
              <td>
                <div class="input-group">
                  <g:select id="unit"  name="unitType" from="${UnitType.values()}" class="form-control" noSelection="['':'Elija la unidad...']" required="" />
                </div>
              </td>
              <td>
                <div class="input-group">
                  <input type="text" id="amount" name="amount" value="0" readonly="" class="form-control" />
                </div>
              </td>
              <td>
                <div class="input-group">
                  <button type="submit" class="btn btn-primary">
                    <i class="fa fa-plus"></i> Agregar
                  </button>
                </div>
              </td>
           </tr>
            </g:if>
            <g:each in="${purchaseOrder.items.sort{it.id}}" var="item">
            <tr>
              <td><h3>${item.quantity}</h3></td>
              <td>${item.name}</td>
              <td>
                <dl class="dl-horizontal">
                  <dt>Precio:</dt>
                  <dd>${modulusuno.formatPrice(number:item.price)}</dd>
                  <dt>IEPS:</dt>
                  <dd>${modulusuno.formatPrice(number:item.amountIEPS)}</dd>
                  <dt>IVA:</dt>
                  <dd>${modulusuno.formatPrice(number:item.amountIVA)}</dd>
                  <dt>Precio Neto:</dt>
                  <dd>${modulusuno.formatPrice(number:item.netPrice)}</dd>
               </dl>
              </td>
              <td>${item.unitType}</td>
              <td class="text-right">${modulusuno.formatPrice(number:item.netAmount)}</td>
              <td></td>
            </tr>
            </g:each>
          </tbody>
          <tfooter>
            <tr>
              <td colspan="6" class="text-right"><strong>Subtotal</strong></td>
              <td class="text-right">
                ${modulusuno.formatPrice(number:purchaseOrder.subtotal)}
              </td>
            </tr>
            <tr>
              <td colspan="6" class="text-right"><strong>IEPS</strong></td>
              <td class="text-right">${modulusuno.formatPrice(number:purchaseOrder.totalIEPS)}</td>
            </tr>
            <tr>
              <td colspan="6" class="text-right"><strong>IVA</strong></td>
              <td class="text-right">${modulusuno.formatPrice(number:purchaseOrder.totalIVA)}</td>
            </tr>
            <tr>
              <td colspan="6" class="text-right"><strong>Total</strong></td>
              <td class="text-right">${modulusuno.formatPrice(number:purchaseOrder.total)}</td>
            </tr>
          </tfooter>
        </table>
        </g:form>
      </div>
    </div>

    <div class="row">
      <g:if test="${params.badfile}">
        <div class="alert alert-warning">${params.badfile}</div>
      </g:if>
     <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
      <g:if test="${enableAddDocuments}">
      <h3>Agregar documentos</h3><h4>${messageDocuments}</h4>
      <g:form class="form-horizontal" action="addDocumentToPurchaseOrder" name="documentForPurchaseOrder" method="POST" enctype="multipart/form-data" id="${purchaseOrder.id}">
      <g:hiddenField name="purchaseOrder.id" value="${purchaseOrder.id}"/>
      <g:if test="${!purchaseOrder.documents}">
        <input type="file" required="" class="form-control" name="invoiceDocument" accept="application/pdf" maxlength="5000000" />
      </g:if><g:else>
        <g:if test="${purchaseOrder.documents?.size()==1}">
          <input type="file" required="" class="form-control" name="invoiceDocument" accept="text/xml" maxlength="5000000" />
        </g:if>
        <g:else>
          <input type="file" required="" class="form-control" name="invoiceDocument" accept="application/pdf,image/*" maxlength="5000000" />
        </g:else>
      </g:else>
     <br/>
      <input type="submit" class="btn btn-default" value="Subir documento de facturación" />
      </g:form>
      </g:if>
      </sec:ifAnyGranted>
      <br>
      <g:if test="${purchaseOrder.documents}">
      <h3>Documentos adjuntos</h3>
      <ul>
        <g:each in="${purchaseOrder.documents}" var="document">
        <li>
         <a href="${baseUrlDocuments}/${document.title}.${document.mimeType}"><i class="glyphicon glyphicon-download-alt"></i> ${document}</a>
        </li>
        </g:each>
      </ul>
      </g:if>
    </div>
    <asset:javascript src="purchaseOrder/show.js"/>
 </body>
</html>
