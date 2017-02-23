<%! import com.modulus.uno.SaleOrderStatus %>

<div class="table-responsive">
  <table class="table table-condensed">
    <tbody>
    <g:hiddenField name="saleOrder.id" value="${saleOrder.id}"/>
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
        <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_OPERATOR_EJECUTOR">
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
    <g:hiddenField name="saleOrder.id" value="${saleOrder.id}"/>
    <tr>
      <td colspan="4">
        <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
          <strong>Descuento a aplicar (%):</strong>
        </g:if>
      </td>
      <td class="text-right"><strong>Subtotal</strong></td>
      <td class="text-right">
        ${modulusuno.formatPrice(number:saleOrder.subtotal)}
      </td>
    </tr>
    <tr>
      <td class="col-xs-2">
        <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
          <input class="form-control" name="discount" type="number" min="0" max="100" value="0" title="El descuento debe estar entre 0 y 100"/>
        </g:if>
      </td>
      <td colspan="3">
        <g:if test="${saleOrder.status == SaleOrderStatus.CREADA}">
          <button type="submit" class="btn btn-info">
            <i class="fa fa-check"></i> Aplicar
          </button>
        </g:if>
      </td>
      <td class="text-right"><strong>Descuento</strong></td>
      <td class="text-right">
        <!--{modulusuno.formatPrice(number:saleOrder.amountDiscount)}-->
      </td>
    </tr>
    <tr>
      <td colspan="4"></td>
      <td class="text-right"><strong>IEPS</strong></td>
      <td class="text-right">
        ${modulusuno.formatPrice(number:saleOrder.totalIEPS)}
      </td>
    </tr>
    <tr>
      <td colspan="4"></td>
      <td class="text-right"><strong>IVA</strong></td>
      <td class="text-right">
        ${modulusuno.formatPrice(number:saleOrder.totalIVA)}
      </td>
    </tr>
    <tr>
      <td colspan="4"></td>
      <td class="text-right"><strong>Total</strong></td>
      <td class="text-right">
        <strong>
          ${modulusuno.formatPrice(number:saleOrder.total)}
        </strong>
      </td>
    </tr>
    </tfooter>
  </table>
</div>

