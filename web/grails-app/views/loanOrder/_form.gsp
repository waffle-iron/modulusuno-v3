<div class="form-group">
  <label for="">Monto</label>
  <div class="input-group col-md-4">
    <div class="input-group-addon input-md">$</div>
    <input type="text" class="form-control input-md" id="amount" name="amount" placeholder="Monto" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto: sin separador de miles, sin decimales, o bien, con dos decimales" value="${loanOrder?.amount}" />
  </div>
</div>
<div class="form-group">
  <label for="">Plazo (en meses)</label>
  <div class="input-group col-md-4">
    <input type="number" class="form-control input-md" id="term" name="term" placeholder="Plazo en meses" required="" pattern="[0-9]" title="Ingrese un número entero" min=1 value="${loanOrder?.term}" />
  </div>
</div>
<div class="form-group">
  <label for="">Tasa Anual</label>
  <div class="input-group col-md-4">
    <div class="input-group-addon input-md">%</div>
    <input type="text" class="form-control input-md" id="rate" name="rate" placeholder="Tasa de interés mensual" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad sin separador de miles, sin decimales, o bien, con dos decimales exactamente" value="${loanOrder?.rate}" />
  </div>
</div>
<div class="form-group">
  <label for="">Deudor</label>
  <div class="input-group col-md-4">
    <g:select name="creditor.id" class="form-control input-md" required="" noSelection="['':'Seleccione el deudor...']" from="${companies.sort{it.bussinessName}}" value="${loanOrder?.creditor?.id}" optionKey="id"/>
  </div>
</div>
