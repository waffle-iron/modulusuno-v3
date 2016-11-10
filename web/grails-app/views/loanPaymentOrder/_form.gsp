<input type="hidden" name="loanOrder.id" value="${loanPaymentOrder?.loanOrder?.id}" />
<input type="hidden" name="company.id" value="${session.company}" />

<div class="row">
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Monto del préstamo</label>
      <div class="input-group">
        ${integradora.formatPrice(number: loanPaymentOrder?.loanOrder?.amount)}
      </div>
    </div>
  </div>
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Fecha del último pago</label>
      <div class="input-group">
        <g:formatDate format="dd-MM-yyyy" date="${lastDatePayment}"/>
      </div>
    </div>
  </div>
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Saldo Actual</label>
      <div class="input-group">
        ${integradora.formatPrice(number: currentBalance)}
        <input type="hidden" id="currentBalance" name="currentBalance" value="${currentBalance}" />
      </div>
    </div>
  </div>

</div>

<div class="row">
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Días del período</label>
      <div class="input-group col-md-4">
        <input type="text" class="form-control input-md" id="daysPeriod" name="daysPeriod" placeholder="Días del período" required="" readonly="" value="${loanPaymentOrder?.daysPeriod}" />
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Intereses</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="amountInterest" name="amountInterest" placeholder="Pago mínimo" required="" readonly="" value="${loanPaymentOrder?.amountInterest}" />
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">IVA de Intereses</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="amountIvaInterest" name="amountIvaInterest" placeholder="IVA de intereses" required="" readonly="" value="${loanPaymentOrder?.amountIvaInterest}" />
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Pago mínimo</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="amountMinPayment" name="amountMinPayment" readonly="" value="${loanPaymentOrder?.amountInterest + loanPaymentOrder?.amountIvaInterest}" />
      </div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Abono a capital</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="amountToCapital" name="amountToCapital" placeholder="Abono a capital" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto: sin separador de miles, sin decimales, o bien, con dos decimales" value="${loanPaymentOrder?.amountToCapital}" />
      </div>
    </div>
  </div>
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Total a pagar</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="amountTotal" name="amountTotal" value="${loanPaymentOrder?.amountToCapital + loanPaymentOrder?.amountInterest + loanPaymentOrder.amountIvaInterest}" readonly="" />
      </div>
    </div>
  </div>
  <div class="col-md-4">
    <div class="form-group">
      <label for="">Nuevo Saldo</label>
      <div class="input-group">
        <div class="input-group-addon input-md">$</div>
        <input type="text" class="form-control input-md" id="newBalance" name="newBalance" value="${currentBalance - loanPaymentOrder?.amountToCapital}" readonly="" />
      </div>
    </div>
  </div>

</div>
