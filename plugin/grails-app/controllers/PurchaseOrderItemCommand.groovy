package com.modulus.uno
import java.text.*

class PurchaseOrderItemCommand {

    String name
    String quantity
    String price
    String ieps
    String iva
    UnitType unitType

    PurchaseOrderItem createPurchaseOrderItem() {
      new PurchaseOrderItem(
          name:this.name,
          quantity:this.quantity,
          price:getValueInBigDecimal(this.price),
          ieps:getValueInBigDecimal(this.ieps?:"16"),
          iva:getValueInBigDecimal(this.iva?:"16"),
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
