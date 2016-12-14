<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'DepositOrder')}" />
  <title><g:message code="default.list.label" args="[entityName]" /></title>
  <asset:stylesheet src="third-party/dropzone/dist/dropzone.css"/>

</head>
<body>
    <div class="page-title">
      <h1>
       <i class="icon-proveedor fa-3x"></i>
        Operaciones | Pago a proveedor
        <small><g:message code="saleOrder.generate.pay" args="[entityName]" /></small>

      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Orden de Compra</li>
      </ol>
    </div>

    <div id="list-company" role="main">
      <form action="${createLink(controller:'purchaseOrder', action:'sendInvoice')}"
          class="dropzone"
          id="my-awesome-dropzone" method="post" enctype="multipart/form-data">
      </form>
      <div class="row-fluid action-buttons-invoice">
        <div class="btn-group col-md-12" align="right">
            <button id="cancel" class="btn btn-warning btn-lg">Cancelar</button>
            <g:link  controller="purchaseOrder" action="saveInvoiceTmp" class="btn btn-primary confirm-btn btn-lg">Confirmar</g:link>
        </div>
      </div>

      <div id="resultInfo"></div>
      <div id="error-display-bank-account"></div>
      <div id="result"></div>

      <script id="error-bank-account" type="text/x-handlebars-templates">
        <br /><br /><br />
        {{#if bank}}
        <div align="center" class="message-provider-bank-account-not-exist">
          <div class="alert alert-danger">
            <g:form controller="businessEntity" action="createAccountByProvider" id="new-bank-account-provider-by-invoice" method="POST">
              {{bank}} <button type="submit" id="newBankAccountProvider" class="btn btn-default">Agregar Cuenta Bancaria o Dirección</button>
              <input type="hidden" name="rfcBank" value="" />
            </g:form>
          </div>
          <br />
        </div>
        {{/if}}
      </script>

      <script id="exist-provider" type="text/x-handlebars-template">
        <br /><br /><br />
        {{#if errors}}
        <div align="center" class="message-provider-not-exist">
          <div class="alert alert-danger">
            <g:form controller="businessEntity" action="create" method="POST" id="new-provider-by-invoice">
              {{errors}} <button type="submit" id="newProvider" class="btn btn-default">Dar de Alta al Prooveedor</button>
              <input type="hidden" name="name" value="{{emisor.nombre}}" />
              <input type="hidden" name="rfc" value="{{emisor.rfc}}" />
              <input type="hidden" name="regimen" value="{{emisor.regimen.regimen}}" />
              <input type="hidden" name="businessName" value="" />
            </g:form>
          </div>
          <br />
        </div>
      {{/if}}
      </script>
      <script id="invoice-template" type="text/x-handlebars-template">
        <input type="hidden" id="nombre" value="{{emisor.nombre}}" />
        <input type="hidden" id="rfc" value="{{emisor.rfc}}" />
        <input type="hidden" id="regimen" value="{{emisor.regimen.regimen}}" />
      <div class="entry">
        <div class="row">
          <div class="col-lg-6">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Emisor</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <dl class="dl-horizontal">
                    {{#with emisor}}
                      <dt>Nombre</dt>
                        <dd>{{#if nombre}} {{nombre}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>RFC</dt>
                        <dd>{{#if rfc}} {{rfc}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Regimen</dt>
                        <dd>{{#if regimen.regimen}} {{regimen.regimen}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    {{/with}}
                  </dl>


                  <dl class="dl-horizontal">
                    <h4>Domicilio fiscal</h4>
                    {{#with emisor.domicilioFiscal}}
                      <dt>Calle<dt>
                        <dd>{{#if calle}} {{calle}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>No. exterior<dt>
                        <dd>{{#if noExterior}} {{noExterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>No. interior<dt>
                        <dd>{{#if noInterior}} {{noInterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Colonia<dt>
                        <dd>{{#if colonia}} {{colonia}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Municipio<dt>
                        <dd>{{#if municipio}} {{municipio}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Codigo postal<dt>
                        <dd>{{#if codigoPostal}} {{codigoPostal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Estado<dt>
                        <dd>{{#if estado}} {{estado}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Pais<dt>
                        <dd>{{#if pais}} {{pais}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    {{/with}}
                  </dl>
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-6">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Receptor</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <dl class="dl-horizontal">
                    {{#with receptor}}
                    <dt>Nombre</dt>
                      <dd>{{#if nombre}} {{nombre}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>RFC</dt>
                      <dd>{{#if rfc}} {{rfc}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    {{/with}}

                    <h4>Domicilio</h4>
                    {{#with receptor.direccionReceptor}}
                    <dt>Calle<dt>
                      <dd>{{#if calle}} {{calle}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>No. exterior<dt>
                      <dd>{{#if noExterior}} {{noExterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>No. interior<dt>
                      <dd>{{#if noInterior}} {{noInterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Colonia<dt>
                      <dd>{{#if colonia}} {{colonia}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Municipio<dt>
                      <dd>{{#if municipio}} {{municipio}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Codigo postal<dt>
                      <dd>{{#if codigoPostal}} {{codigoPostal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Estado<dt>
                      <dd>{{#if estado}} {{estado}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Pais<dt>
                      <dd>{{#if pais}} {{pais}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    {{/with}}
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="entry">
        <div class="row">
          <div class="col-lg-12">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Conceptos</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <div class="table-responsive">
                    <table class="table">
                    <thead>
                      <tr>
                        <th>Cantidad</th>
                        <th>Unidad</th>
                        <th>Descripcion</th>
                        <th>Valor unitario</th>
                        <th>Importe</th>
                        <th>&nbsp;</th>
                      </tr>
                    </thead>
                    <tbody>
                      <dl>
                      {{#each conceptos}}
                      <tr>
                        <td>
                          <div class="input-group">
                            <dd>{{#if cantidad}} {{cantidad}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </div>
                        </td>
                        <td>
                          <div class="input-group">
                            <dd>{{#if unidad}} {{unidad}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </div>
                        </td>
                        <td>
                          <div class="input-group">
                            <dd>{{#if descripcion}} {{descripcion}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </div>
                        </td>
                        <td>
                          <div class="input-group">

                            <dd>{{#if valorUnitario}} {{formatMoney valorUnitario}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </div>
                        </td>
                        <td>
                          <div class="input-group">
                            <dd>{{#if importe}} {{formatMoney importe}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </div>
                        </td>
                      </tr>
                      {{/each}}
                      <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>Sub total</th>
                        <td>
                          <dd>{{#if subTotal}} {{formatMoney subTotal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                        </td>
                        <th>&nbsp;</th>
                      </tr>
                      {{#each impuesto.traslado}}
                        <tr>
                          <th></th>
                          <th></th>
                          <th></th>
                          <th>
                            <dd>{{#if impuesto}} {{impuesto}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </th>
                          <td>
                            <dd>{{#if importe}} {{formatMoney importe}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          </td>

                          </td>
                          <th>&nbsp;</th>
                        </tr>
                      {{/each}}
                      <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>Total</th>
                        <td>
                          <dd>{{#if total}} {{formatMoney total}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                        </td>
                        <th>&nbsp;</th>
                      </tr>
                      </dl>
                    </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="entry">
        <div class="row">
          <div class="col-lg-12">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Addenda</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <dl class="dl-horizontal" >
                    {{#with addenda.estadoDeCuentaBancario}}
                      <dt>Nombre cliente<dt>
                        <dd>{{#if nombreCliente}} {{nombreCliente}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Periodo<dt>
                        <dd>{{#if periodo}} {{periodo}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Version<dt>
                        <dd>{{#if version}} {{version}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Sucursal<dt>
                        <dd>{{#if sucursal}} {{sucursal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>No. cuenta<dt>
                        <dd>{{#if numeroCuenta}} {{numeroCuenta}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    </dl>
                    <dl class="dl-horizontal" >
                      <h4>Informacion</h4>
                        {{#each movimientoECB}}
                          <dt>Fecha</dt>
                            <dd>{{#if fecha}} {{fecha}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Referencia</dt>
                            <dd>{{#if referencia}} {{referencia}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Descripcion</dt>
                            <dd>{{#if descripcion}} {{descripcion}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Importe</dt>
                            <dd>{{#if importe}} {{importe}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Moneda</dt>
                            <dd>{{#if moneda}} {{moneda}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Saldo inicial</dt>
                            <dd>{{#if saldoInicial}} {{saldoInicial}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <dt>Saldo al corte</dt>
                            <dd>{{#if saldoAlCorte}} {{saldoAlCorte}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                          <br/>
                        {{/each}}
                    {{/with}}
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="entry">
        <div class="row">
          <div class="col-lg-12">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Informacion general</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <dl class="dl-horizontal">
                    <dt>Fecha</dt>
                      <dd>{{#if fecha}} {{fecha}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Subtotal</dt>
                      <dd>{{#if subTotal}} {{subTotal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Descuento</dt>
                      <dd>{{#if descuento}} {{descuento}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Impuesto</dt>
                      <dd>{{#if impuesto.totalImpuestosTrasladado}} {{impuesto.totalImpuestosTrasladado}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Total</dt>
                      <dd>{{#if total}} {{total}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Lugar expedicion</dt>
                      <dd>{{#if lugarExpedicion}} {{lugarExpedicion}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Moneda</dt>
                      <dd>{{#if moneda}} {{moneda}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Metodo de pago</dt>
                      <dd>{{#if metodoDePago}} {{metodoDePago}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>No. certificado</dt>
                      <dd>{{#if noCertificado}} {{noCertificado}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Serie</dt>
                      <dd>{{#if serie}} {{serie}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Folio</dt>
                      <dd>{{#if folio}} {{folio}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Tipo de comprobante</dt>
                      <dd>{{#if tipoDeComprobante}} {{tipoDeComprobante}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>No. cuenta</dt>
                      <dd>{{#if numCtaPago}} {{numCtaPago}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    <dt>Forma de pago</dt>
                      <dd>{{#if formaDePago}} {{formaDePago}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="entry">
        <div class="row">
          <div class="col-lg-12">
            <div class="portlet portlet-default">
              <div class="portlet-heading">
                <div class="portlet-title">
                  <h4>Emisor complemento</h4>
                </div>
                <div class="clearfix"></div>
              </div>
              <div id="defaultPortlet" class="panel-collapse collapse in">
                <div class="portlet-body">
                  <dl class="dl-horizontal">
                    <h4>Expedido en</h4>
                    {{#with emisor.lugarExpedicion}}
                      <dt>Calle</dt>
                        <dd>{{#if calle}} {{calle}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>No. exterior</dt>
                        <dd>{{#if noExterior}} {{noExterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>No. interior</dt>
                        <dd>{{#if noInterior}} {{noInterior}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Colonia</dt>
                        <dd>{{#if colonia}} {{colonia}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Municipio</dt>
                        <dd>{{#if municipio}} {{municipio}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Codigo postal</dt>
                        <dd>{{#if codigoPostal}} {{codigoPostal}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Estado</dt>
                        <dd>{{#if estado}} {{estado}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                      <dt>Pais</dt>
                        <dd>{{#if pais}} {{pais}} {{else}} <span class="text-danger"><strong>Sin datos</strong></span> {{/if}}</dd>
                    {{/with}}
                  </dl>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      </div>
  </script>

  <asset:javascript src="third-party/handlebars/handlebars.js"/>
  <asset:javascript src="third-party/dropzone/dist/dropzone.js"/>
  <asset:javascript src="configurationDropzone.js"/>
  <asset:javascript src="saleOrder/format_helper.js"/>
  <asset:javascript src="third-party/handlebars-helper-intl/dist/handlebars-intl.min.js"/>
  <asset:javascript src="saleOrder/sale_order_by_invoice.js"/>
</body>
</html>
