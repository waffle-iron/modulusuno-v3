package com.modulus.uno
import java.text.*
import grails.validation.Validateable

class MovimientosBancariosCommand implements Validateable {

  String concept
  String reference
  String amount
  String dateEvent
  String type

  MovimientosBancarios createObject() {
    new MovimientosBancarios(
      concept:this.concept,
      reference:this.reference ?: "",
      amount: getValueInBigDecimal(this.amount),
      type:MovimientoBancarioType."${this.type}",
      dateEvent: Date.parse('dd/MM/yyyy',this.dateEvent)
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
