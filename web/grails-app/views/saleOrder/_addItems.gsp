<%! import com.modulus.uno.UnitType %>
<%! import com.modulus.uno.SaleOrderStatus %>

<div class="table-responsive">
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
            <input id="quantity" name="quantity" class="form-control" type="number" min="0.01" step="0.01" required="" />
          </div>
        </td>
        <td>
          <div class="input-group easy-autocomplete col-xs-12">
            <input id="product-name" name="name" type="text" class="form-control" autocomplete="off" placeholder="Nombre del producto" required="" maxlength="300" />
          </div>
          <div class="input-group easy-autocomplete col-xs-10">
            <input type="text" id="sku" name="sku" class="form-control" autocomplete="off" placeholder="SKU" required="" pattern=".{4,50}" maxlength="50" title="4 caracteres mínimo"/>
          </div>
        </td>
        <td>
          <div class="input-group">
            <div class="input-group-addon">$</div>
            <input type="text" id="price" name="price" class="form-control" required="" pattern="[0-9]+(\.[0-9]{1,2})?" title="Ingrese una cantidad en formato correcto (número sin decimales o con 2 decimales exactamente)"/>
          </div>
          <div class="input-group">
            <div class="input-group-addon">%</div>
            <input type="text" id="discount" name="discount" class="form-control" value="0" required="" pattern="[0-9]+(\.[0-9]{1,2})?"/>
            <div class="input-group-addon">DESC</div>
          </div>
          <div class="input-group">
            <div class="input-group-addon">%</div>
            <input type="text" id="ieps" name="ieps" class="form-control" required="" pattern="[0-9]+(\.[0-9]{1,2})?"/>
            <div class="input-group-addon">IEPS</div>
          </div>
          <div class="input-group">
            <div class="input-group-addon">%</div>
            <input type="text" id="iva" name="iva" class="form-control" required="" pattern="[0-9]+(\.[0-9]{1,2})?"/>
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
    </tbody>
  </table>
</div>

