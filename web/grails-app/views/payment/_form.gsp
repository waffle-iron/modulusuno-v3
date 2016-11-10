<f:with bean="payment">
  <div class="form-group">
    <label for="">${message(code:"payment.company")}</label>
    <g:select class="form-control" name="company" from="${companies.sort{it.bussinessName}}" value="${payment.company}" optionKey="id" required="" noSelection="['':'']"/>
  </div>
  <div class="form-group">
    <label for="">${message(code:"payment.amount")}</label>
    <input type="text" class="form-control" name="amount" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto"/>
  </div>

</f:with>

