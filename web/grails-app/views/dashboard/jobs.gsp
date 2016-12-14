<!doctype html>
<html>
  <head>
    <title>Modulus UNO | Servicios Financieros</title>
    <meta name="layout" content="main">
  </head>
  <body>

    <div class="page-title">
      <h1>
        <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">
          <i class="fa fa-tachometer fa-3x"></i>
          Administración
          <small>Operaciones</small>
        </sec:ifAnyGranted>
        <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
          <i class="fa fa-tachometer fa-3x"></i>
          Autorizaciones
          <small>Órdenes por Autorizar de la Compañía</small>
        </sec:ifAnyGranted>
      </h1>
    </div>

    <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">
            <div class="circle-tile-heading dark-blue">
              <i class="fa icon-deposito fa-3x"></i>
            </div>
          <div class="circle-tile-content green">
            <div class="circle-tile-description">
              Depósitos
              <span>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${depositOrderAuthorizedCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${depositOrderToAuthorizeCount}</sec:ifAnyGranted>
            </div>
            <div class="text-right">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:link controller="depositOrder" action="list" params="[status:'AUTHORIZED']" class="btn btn-green btn-xs">
                  Ver pendientes
                </g:link>
                <g:link controller="depositOrder" action="list" class="btn btn-green btn-xs">
                  Ver todas
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_EJECUTOR">
                <g:link controller="depositOrder" action="list" params="[status:'AUTHORIZED']" class="btn btn-green btn-xs">
                  Por Ejecutar
                </g:link>
                </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:link controller="depositOrder" action="list" params="[status:'VALIDATE']" class="btn btn-green btn-xs">
                  Ver por autorizar
                </g:link>
              </sec:ifAnyGranted>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">

            <div class="circle-tile-heading blue">
              <i class="fa icon-retiro fa-3x"></i>
            </div>

          <div class="circle-tile-content blue">
            <div class="circle-tile-description">
              Retiros
              <span>
                 <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number ">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${cashOutOrderAuthorizedCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${cashOutOrderToAuthorizeCount}</sec:ifAnyGranted>
            </div>
        <div class="text-right">
            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
              <g:link controller="cashOutOrder" params="[status:'AUTHORIZED']" action="list" class="btn btn-green btn-xs">
                Ver autorizadas
              </g:link>
              <g:link controller="cashOutOrder" action="list" class="btn btn-green btn-xs">
                Ver todas
              </g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_EJECUTOR">
              <g:link controller="cashOutOrder" params="[status:'AUTHORIZED']" action="list" class="btn btn-green btn-xs">
                Por Ejecutar
              </g:link>
            </sec:ifAnyGranted>
            <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
              <g:link controller="cashOutOrder" params="[status:'TO_AUTHORIZED']" action="list" class="btn btn-green btn-xs">
                Ver por autorizar
              </g:link>
            </sec:ifAnyGranted>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">

            <div class="circle-tile-heading purple">
              <i class="fa icon-factura fa-3x"></i>
            </div>

          <div class="circle-tile-content purple">
            <div class="circle-tile-description">
              Cobranza
              <span>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${saleOrderAuthorizedCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${saleOrderToAuthorizeCount}</sec:ifAnyGranted>
            </div>
<div class="text-right">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:link controller="saleOrder" params="[status:'AUTORIZADA']" action="list" class="btn btn-green btn-xs">
                  Ver autorizadas
                </g:link>
                <g:link controller="saleOrder" action="list" class="btn btn-green btn-xs">
                  Ver todas
                </g:link>
                 <br/>
                <g:link controller="saleOrder" params="[status:'CANCELACION_AUTORIZADA']" action="list" class="btn btn-green btn-xs">
                  Ver Cancelaciones por ejecutar <strong>[${saleOrderToCancelBillForExecuteCount}]</strong>
                </g:link>
             </sec:ifAnyGranted>
             <sec:ifAnyGranted roles="ROLE_EJECUTOR">
               <g:link controller="saleOrder" params="[status:'AUTORIZADA']" action="list" class="btn btn-green btn-xs">
                 Por Ejecutar
               </g:link>
                <br/>
               <g:link controller="saleOrder" params="[status:'CANCELACION_AUTORIZADA']" action="list" class="btn btn-green btn-xs">
                 Ver Cancelaciones por ejecutar <strong>[${saleOrderToCancelBillForExecuteCount}]</strong>
               </g:link>
            </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:link controller="saleOrder" params="[status:'POR_AUTORIZAR']" action="list" class="btn btn-green btn-xs">
                  Ver por autorizar
                </g:link>
                <br/>
                <g:link controller="saleOrder" params="[status:'CANCELACION_POR_AUTORIZAR']" action="list" class="btn btn-green btn-xs">
                  Ver Cancelaciones por autorizar <strong>[${saleOrderToCancelBillForAuthorizeCount}]</strong>
                </g:link>
             </sec:ifAnyGranted>
              </div>
          </div>
        </div>
      </div>



      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">

            <div class="circle-tile-heading dark-blue">
              <i class="fa icon-proveedor fa-3x"></i>
            </div>

          <div class="circle-tile-content green">
            <div class="circle-tile-description">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">Pagos</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">Compras</sec:ifAnyGranted>
              <span>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${purchaseOrderAuthorizedCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${purchaseOrderToAuthorizeCount}</sec:ifAnyGranted>
            </div>
<div class="text-right">

              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:link controller="purchaseOrder" action="list" params="[status:'AUTORIZADA']" class="btn btn-green btn-xs">
                  Ver autorizadas
                </g:link>
                <g:link controller="purchaseOrder" action="list" class="btn btn-green btn-xs">
                  Ver todas
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_EJECUTOR">
                <g:link controller="purchaseOrder" action="list" params="[status:'AUTORIZADA']" class="btn btn-green btn-xs">
                  Por Ejecutar
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:link controller="purchaseOrder" action="list" params="[status:'POR_AUTORIZAR']" class="btn btn-green btn-xs">
                  Ver por autorizar
                </g:link>
              </sec:ifAnyGranted>
</div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">

            <div class="circle-tile-heading blue">
              <i class="fa icon-reembolso fa-3x"></i>
            </div>

          <div class="circle-tile-content blue">
            <div class="circle-tile-description">
              Reembolsos
              <span>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${moneyBackOrderAuthorizedCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${moneyBackOrderToAuthorizeCount}</sec:ifAnyGranted>
            </div>
<div class="text-right">

              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:link controller="purchaseOrder" action="listMoneyBackOrders" params="[status:'AUTORIZADA']" class="btn btn-green btn-xs">
                  Ver autorizadas
                </g:link>
                <g:link controller="purchaseOrder" action="listMoneyBackOrders" class="btn btn-green btn-xs">
                  Ver todas
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_EJECUTOR">
                <g:link controller="purchaseOrder" action="listMoneyBackOrders" params="[status:'AUTORIZADA']" class="btn btn-green btn-xs">
                  Por Ejecutar
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:link controller="purchaseOrder" action="listMoneyBackOrders" params="[status:'POR_AUTORIZAR']" class="btn btn-green btn-xs">
                  Ver por autorizar
                </g:link>
              </sec:ifAnyGranted>
</div>
          </div>
        </div>
      </div>

      <div class="col-lg-4 col-md-6 col-sm-12">
        <div class="circle-tile">

          <div class="circle-tile-heading dark-blue">
            <i class="fa icon-recibo fa-3x"></i>
          </div>

          <div class="circle-tile-content green">
            <div class="circle-tile-description">
              Recibos de Honorarios
              <span>
                <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">por ejecutar</sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">por autorizar</sec:ifAnyGranted>
              </span>
            </div>
            <div class="circle-tile-number">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE,ROLE_EJECUTOR">${feesReceiptCount}</sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">${feesReceiptToAuthorizeCount}</sec:ifAnyGranted>
            </div>
            <div class="text-right">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:link controller="feesReceipt" action="listToExecute" class="btn btn-green btn-xs">
                Ver pendientes
                </g:link>
                <g:link controller="feesReceipt" action="listAll" class="btn btn-green btn-xs">
                Ver todas
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_EJECUTOR">
                <g:link controller="feesReceipt" action="listToExecute" class="btn btn-green btn-xs">
                  Por Ejecutar
                </g:link>
              </sec:ifAnyGranted>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <g:link controller="feesReceipt" action="listToAuthorize" class="btn btn-green btn-xs">
                Ver  por autorizar
                </g:link>
              </sec:ifAnyGranted>
            </div>
          </div>
        </div>
      </div>

  </body>
</html>
