
<ul class="collapse nav" id="operaciones-${action}">
  <li>
    <a href="javascript:;" data-parent="#operaciones-${action}" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesDeposito-${action}">
      Depósitos <i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="ordenesDeposito">
      <li>
        <g:link controller="depositOrder" action="create">Nueva</g:link>
      </li>
      <li>
        <g:link controller="depositOrder" action="list"> Listado</g:link>
      </li>
    </ul>
  </li>
  <li>
  <li>
    <a href="javascript:;" data-parent="#operaciones-${action}" data-toggle="collapse" class="accordion-toggle" data-target="#cashoutOrder-${action}">
      Retiros <i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="cashoutOrder">
      <li>
        <g:link controller="cashOutOrder" action="create">Nueva</g:link>
      </li>
      <li>
        <g:link controller="cashOutOrder" action="list">Listado</g:link>
      </li>
    </ul>
  </li>
  <li>
    <a href="javascript:;" data-parent="#operaciones-${action}" data-toggle="collapse" class="accordion-toggle" data-target="#saleOrder-${action}">
      Facturas <i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="saleOrder">
      <li>
        <g:link controller="saleOrder" action="create">Nueva</g:link>
      </li>
      <li>
        <g:link controller="saleOrder" action="list">Listado</g:link>
      </li>
      <li>
        <g:link controller="payment" action="reconcile">Conciliaciones</g:link>
      </li>
    </ul>
  </li>


  <li>
    <a href="javascript:;" data-parent="#operaciones-${action}" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesCompra-${action}">
      Órdenes de Compra <i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="ordenesCompra">
      <li>
        <g:link controller="purchaseOrder" action="create">Nueva</g:link>
      </li>
      <li>
        <g:link controller="purchaseOrder" action="list"> Listado</g:link>
      </li>
    </ul>
  </li>
  <li>
    <a href="javascript:;" data-parent="#operaciones-${action}" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesReembolso-${action}">
      Reembolsos a Empleados <i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="ordenesReembolso">
      <li>
        <g:link controller="purchaseOrder" action="createMoneyBackOrder">Nueva</g:link>
      </li>
      <li>
        <g:link controller="purchaseOrder" action="listMoneyBackOrders"> Listado</g:link>
      </li>
    </ul>
  </li>
  <li>
    <a href="javascript:;" data-parent="#consultas" data-toggle="collapse" class="accordion-toggle" data-target="#feesReceipt-${action}">
      Recibo de Honorarios<i class="fa fa-caret-down"></i>
    </a>
    <ul class="collapse nav" id="feesReceipt">
      <li>
        <g:link controller="feesReceipt" action="list">
        Todas</a>
        </g:link>
      </li>
      <li>
        <g:link controller="feesReceipt" action="list" params="[status:'CREADA']">
         Creadas
        </g:link>
      </li>
    </ul>
  </li>
  <li>
    <g:link controller="movimientosBancarios" >Movimiento</g:link>
  </li>
  <li>
    <g:link controller="movimientosBancarios" action="multiMovimientos" >Subir Movimientos Bancarios</g:link>
  </li>
</ul>
