<div class="form-group">
  <label>Concepto del Movimiento</label>
  <input type"text" name="concept" class="form-control" required="required"/>
</div>
<div class="form-group">
  <label>Referencia</label>
  <input type"text" name="reference" class="form-control" />
</div>
<div class="form-group">
  <label>Monto del Movimiento</label>
  <input type="text" name="amount" class="form-control"  required="required"/>
</div>
<div class="form-group">
  <label>Fecha del movimiento</label>
  <input type="text" id="datepicker" name="dateEvent" required="required">
</div>
<div class="form-group">
  <label>Tipo de transaccion</label>
  <g:select name="type" from="${com.modulus.uno.MovimientoBancarioType.values()}" class="form-control" />
</div>
<div class="form-group">
  <label>Cuenta</label>
  <b>${movimientosBancarios.cuenta}</b>
</div>
