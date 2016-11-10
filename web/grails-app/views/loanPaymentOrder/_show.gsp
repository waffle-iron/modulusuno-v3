<div class="row">
  <div class="col-md-3">
    <div class="form-group">
      <label for="">No. de Orden</label>
      <div class="input-group">
        ${loanPaymentOrder.id}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Acreedor</label>
      <div class="input-group">
        ${loanPaymentOrder.loanOrder.company}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Monto del préstamo</label>
      <div class="input-group">
        ${integradora.formatPrice(number: loanPaymentOrder?.loanOrder?.amount)}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Saldo Actual</label>
      <div class="input-group">
        ${integradora.formatPrice(number: currentBalance)}
      </div>
    </div>
  </div>

</div>

<div class="row">
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Días del período</label>
      <div class="input-group col-md-4">
        ${loanPaymentOrder.daysPeriod}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Intereses</label>
      <div class="input-group">
        ${integradora.formatPrice(number: loanPaymentOrder.amountInterest)}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">IVA de Intereses</label>
      <div class="input-group">
        ${integradora.formatPrice(number:loanPaymentOrder.amountIvaInterest)}
      </div>
    </div>
  </div>
  <div class="col-md-3">
    <div class="form-group">
      <label for="">Pago mínimo</label>
      <div class="input-group">
        ${integradora.formatPrice(number:loanPaymentOrder.amountInterest + loanPaymentOrder.amountIvaInterest)}
      </div>
    </div>
  </div>
</div>

<div class="row">
  <div class="col-md-6">
    <div class="form-group">
      <label for="">Abono a capital</label>
      <div class="input-group">
        ${integradora.formatPrice(number:loanPaymentOrder.amountToCapital)}
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <div class="form-group">
      <label for="">Total a pagar</label>
      <div class="input-group">
        ${integradora.formatPrice(number:loanPaymentOrder.amountToCapital + loanPaymentOrder.amountInterest + loanPaymentOrder.amountIvaInterest)}
      </div>
    </div>
  </div>
</div>
