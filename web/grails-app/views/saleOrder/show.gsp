<%! import com.modulus.uno.UnitType %>
<%! import com.modulus.uno.SaleOrderStatus %>
<%! import com.modulus.uno.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'saleOrder.label', default: 'SaleOrder')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <!-- JS file -->
    <script src="/assets/third-party/EasyAutocomplete/dist/jquery.easy-autocomplete.min.js"></script>
    <!-- CSS file -->
    <link rel="stylesheet" href="/assets/third-party/EasyAutocomplete/dist/easy-autocomplete.min.css">
    <!--sobreescribir estilo selected-->
    <style>
#eac-container-product-name li.selected {
  text-decoration: none;
}
      .behind {
        z-index:0;
      }
    </style>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="saleOrder.show"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">
          <g:link controller="saleOrder" action="index" params="[companyId:saleOrder.company.id]">${saleOrder.company}</g:link>
        </li>
        </li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-6">
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos del cliente</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <dl class="dl-horizontal">
                <dt>RFC:</dt>
                <dd>${saleOrder.rfc}</dd>
                <dt>A nombre de:</dt>
                <dd>${saleOrder.clientName}</dd>
                <g:each in="${saleOrder.addresses}" var="address">
                <dt>Dirección:</dt>
                <dd>
                ${address.street}<br>
                ${address.streetNumber} ${address.suite}<br>
                ${address.colony}<br>
                ${address.country}, ${address.federalEntity}<br>
                ${address.zipCode}<br>
                ${address.city}, ${address.town}<br>
                </dd>
                </g:each>
              </dl>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-6">
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos de orden de venta</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <dl class="dl-horizontal">
                <dt>No. de Orden</dt>
                <dd>
                ${saleOrder.id}
                </dd>
                <dt>Cantidad de detalles</dt>
                <dd>
                ${saleOrder.items.size()}
                </dd>
                <dt>Subtotal</dt>
                <dd>
                ${modulusuno.formatPrice(number:saleOrder.subtotal)}
                </dd>
                <dt>IVA</dt>
                <dd>${modulusuno.formatPrice(number:saleOrder.totalIVA)}</dd>
                <dt>IEPS</dt>
                <dd>${modulusuno.formatPrice(number:saleOrder.totalIEPS)}</dd>
                <dt>Total</dt>
                <dd>${modulusuno.formatPrice(number:saleOrder.total)}</dd>
                <dt>Estado</dt>
                <dd><g:message code="saleOrder.status.${saleOrder.status}" default="${saleOrder.status}"/></dd>
              </dl>
              <p>
              <g:if test="${saleOrder.status == SaleOrderStatus.CANCELADA || saleOrder.status == SaleOrderStatus.RECHAZADA}">
              <div class="alert alert-danger" role="alert">
                <label class="control-label">Motivo ${message(code:'saleOrder.rejectReason.'+saleOrder.status)}:</label>
                <g:message code="rejectReason.${saleOrder.rejectReason}" default="${saleOrder.rejectReason}"/>
                <textarea class="form-control" rows="3" cols="60" readonly>${saleOrder.comments}</textarea>
              </div>
              </g:if>
              </p>

              <p>
              <g:if test="${saleOrder.items}">

                <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
                  <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
                    <g:form controller="saleOrder" action="sendOrderToConfirmation" id="${saleOrder.id}">
                    <button type="submit" class="btn btn-success btn-block">
                      Confirmar orden de venta
                    </button>
                    </g:form>
                  </g:if>

                  <g:if test="${saleOrder.status == SaleOrderStatus.EJECUTADA}">
                    <g:link class="btn btn-danger" action="requestCancelBill" id="${saleOrder.id}">Solicitar Cancelación de Factura</g:link>
                    <p>
                  </g:if>
                </sec:ifAnyGranted>

              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:if test="${saleOrder.status == SaleOrderStatus.POR_AUTORIZAR}">
                  <g:if test="!saleOrder.authorizations?.find{it.user == user}}">
                    <div class="container-fluid">
                      <div class="row">
                        <div class="col-md-6">
                          <g:link action="authorizeSaleOrder" class="btn btn-warning btn-block" id="${saleOrder.id}">Autorizar la Orden de Venta</g:link>
                        </div>
                        <div class="col-md-6">
                          <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar la Orden de Venta</a>
                        </div>
                        <div class="row">
                          <div class="col-md-12">
                            <div class="collapse" id="inputReasonCancellation">
                              <div class="well">
                                <g:form action="cancelSaleOrder" id="${saleOrder.id}">
                                <div class="form-group">
                                  <g:select name="rejectReason" from="${RejectReason.values()}" value="${saleOrder.rejectReason}" class="form-control" />
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
                  </g:if>
                  <g:else>
                    <div class="alert alert-warning"><g:message code="order.already.authorized"/></div>
                  </g:else>
                </g:if>

                <g:if test="${saleOrder.status == SaleOrderStatus.CANCELACION_POR_AUTORIZAR}">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-md-9">
                        <g:link action="authorizeCancelBill" class="btn btn-warning btn-block" id="${saleOrder.id}">Autorizar Cancelación de Factura</g:link>
                      </div>
                    </div>
                  </div>
                </g:if>
              </sec:ifAnyGranted>

              <sec:ifAnyGranted roles="ROLE_ADMIN_IECCE">
                <g:if test="${saleOrder.status == SaleOrderStatus.AUTORIZADA}">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-md-6">
                        <g:link action="executeSaleOrder" class="btn btn-success btn-block" id="${saleOrder.id}">Ejecutar orden de venta</g:link>
                      </div>
                      <div class="col-md-6">
                        <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Rechazar la orden de venta</a>
                      </div>
                      <div class="row">
                        <div class="col-md-12">
                          <div class="collapse" id="inputReasonCancellation">
                            <div class="well">
                              <g:form action="rejectSaleOrder" id="${saleOrder.id}">
                              <div class="form-group">
                                <g:select name="rejectReason" from="${RejectReason.values()}" value="${saleOrder.rejectReason}" class="form-control" />
                                <br/>
                                <g:textArea name="comments" placeholder="Comentarios opcionales" rows="3" cols="60" maxLength="255" class="form-control"/>
                                <br/>
                                <button type="submit" class="btn btn-danger">Rechazar</button>
                              </div>
                                </g:form>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </g:if>

                <g:if test="${saleOrder.status == SaleOrderStatus.CANCELACION_AUTORIZADA}">
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-md-9">
                        <g:link action="executeCancelBill" class="btn btn-danger btn-block" id="${saleOrder.id}">Ejecutar Cancelación de Factura</g:link>
                      </div>
                    </div>
                  </div>
                </g:if>
              </sec:ifAnyGranted>

              <g:if test="${saleOrder.status == SaleOrderStatus.EJECUTADA || saleOrder.status == SaleOrderStatus.PAGADA}">
                <a href="${modulusuno.invoiceUrl(saleOrder:saleOrder, format:'xml')}" class="btn btn-success">Descarga factura XML</a>
                <a href="${modulusuno.invoiceUrl(saleOrder:saleOrder, format:'pdf')}" class="btn btn-default">Descarga factura PDF</a>
              </g:if>
              </g:if>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <g:if test="${flash.message}">
      <div class="alert alert-info">${flash.message}</div>
      </g:if>
      <div class="table-responsive">
        <g:form controller="saleOrderItem" action="save">
        <table class="table table-condensed">
          <thead>
            <tr>
              <th class="col-xs-1">Cantidad</th>
              <th class="col-xs-4">Descripción del producto</th>
              <th class="col-xs-3">Precio Unitario</th>
              <th class="col-xs-2">Medida</th>
              <th class="col-xs-2">Importe</th>
              <th>&nbsp;</th>
            </tr>
          </thead>
          <tbody>
            <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
            <g:hiddenField name="saleOrder.id" value="${saleOrder.id}"/>
            <tr>
              <td>
                <div class="input-group">
                  <input id="quantity" name="quantity" class="form-control" type="number" min="1" required="" />
                </div>
              </td>
              <td>
                <div class="input-group easy-autocomplete col-xs-12">
                  <input id="product-name" name="name" type="text" class="form-control" autocomplete="off" placeholder="Nombre del producto" required="" maxlength="200" />
                </div>
                <div class="input-group col-xs-10">
                  <div class="input-group-addon">SKU</div>
                  <g:textField id="sku" name="sku" class="form-control" required="" pattern=".{4,50}" title="4 caracteres mínimo" class="behind"/>
                </div>
              </td>
              <td>
                <div class="input-group">
                  <div class="input-group-addon">$</div>
                  <input type="text" id="price" name="price" class="form-control" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto"/>
                </div>
                <div class="input-group">
                  <div class="input-group-addon">%</div>
                  <input type="text" id="ieps" name="ieps" class="form-control" required="" pattern="[0-9]+(\.[0-9]{2})?"/>
                  <div class="input-group-addon">IEPS</div>
                </div>
                <div class="input-group">
                  <div class="input-group-addon">%</div>
                  <input type="text" id="iva" name="iva" class="form-control" required="" pattern="[0-9]+(\.[0-9]{2})?"/>
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
                  <g:each in="${saleOrder.items.sort{it.id}}" var="item">
                  <tr>
                    <td><h3>${item.quantity}</h3></td>
                    <td>
                      ${item.name}<br/>
                      <small><i>${item.sku}</i></small>
                    </td>
                    <td>
                      <dl class="dl-horizontal">
                        <dt>Precio:</dt>
                        <dd>${modulusuno.formatPrice(number:item.price)}</dd>
                        <dt>IEPS:</dt>
                        <dd>${modulusuno.formatPrice(number:item.amountIEPS)}</dd>
                        <dt>IVA:</dt>
                        <dd>${modulusuno.formatPrice(number:item.amountIVA)}</dd>
                        <dt>Neto:</dt>
                        <dd>${modulusuno.formatPrice(number:item.netPrice)}</dd>
                      </dl>
                    </td>
                    <td>${item.unitType}</td>
                    <td class="text-right">
                      <strong>${modulusuno.formatPrice(number:item.netAmount)}</strong>
                    </td>
                    <td class="text-center">
                      <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
                        <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
                          <g:link action="deleteItem" id="${item.id}" class="btn btn-danger">
                            <i class="fa fa-minus"></i> Quitar
                          </g:link>
                        </g:if>
                      </sec:ifAnyGranted>
                    </td>
                  </tr>
                  </g:each>
          </tbody>
          <tfooter>
          <tr>
            <td colspan="5" class="text-right"><strong>Subtotal</strong></td>
            <td class="text-right">
              ${modulusuno.formatPrice(number:saleOrder.subtotal)}
            </td>
          </tr>
          <tr>
            <td colspan="5" class="text-right"><strong>IEPS</strong></td>
            <td class="text-right">
              ${modulusuno.formatPrice(number:saleOrder.totalIEPS)}
            </td>
          </tr>
          <tr>
            <td colspan="5" class="text-right"><strong>IVA</strong></td>
            <td class="text-right">
              ${modulusuno.formatPrice(number:saleOrder.totalIVA)}
            </td>
          </tr>
          <tr>
            <td colspan="5" class="text-right"><strong>Total</strong></td>
            <td class="text-right">
              <strong>
                ${modulusuno.formatPrice(number:saleOrder.total)}
              </strong>
            </td>
          </tr>
          </tfooter>
        </table>
                  </g:form>
      </div>
    </div>
    </div>
    <asset:javascript src="saleOrder/show.js"/>
  </body>
</html>
