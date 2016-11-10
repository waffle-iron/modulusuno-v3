package com.modulus.uno
import java.text.*

class SaleOrderItemCommand {

  String sku
  String name
  String quantity
  String price
  String ieps
  String iva
  UnitType unitType

  SaleOrderItem createSaleOrderItem() {
    new SaleOrderItem(
      sku:this.sku,
      name:this.name,
      quantity:this.quantity,
      price:getValueInBigDecimal(this.price),
      ieps:getValueInBigDecimal(this.ieps?:"0"),
      iva:getValueInBigDecimal(this.iva?:"0"),
      unitType:this.unitType
    )
  }

  private def getValueInBigDecimal(String value) {
    Locale.setDefault(new Locale("es","MX"));
    DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
    df.setParseBigDecimal(true);
    BigDecimal bd = (BigDecimal) df.parse(value);
    bd
  }

}
