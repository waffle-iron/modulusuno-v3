package com.modulus.uno

class PurchaseOrderItem {

  String name
  Integer quantity = 1
  BigDecimal price = new BigDecimal(0)
  BigDecimal ieps = new BigDecimal(0)
  BigDecimal iva = new BigDecimal(16)

  UnitType unitType
  CurrencyType currencyType = CurrencyType.PESOS

  static belongsTo = [purchaseOrder:PurchaseOrder]

  static constraints = {
    name blank:false,size:1..100
    price min:0.0,max:250000000.00
    ieps min:0.0,max:100.01
    iva min:0.0,max:100.00
    quantity min:0
  }

  BigDecimal getAmountIVA(){
    this.price * (this.iva/100)
  }

  BigDecimal getAppliedIVA() {
    this.quantity * this.getAmountIVA()
  }

  BigDecimal getAmountIEPS(){
    this.price * (this.ieps/100)
  }

  BigDecimal getAppliedIEPS() {
    this.quantity * this.getAmountIEPS()
  }

  BigDecimal getAmountWithoutTaxes(){
    this.quantity * this.price
  }

  BigDecimal getNetPrice(){
    this.price + this.getAmountIVA() + this.getAmountIEPS()
  }

  BigDecimal getNetAmount(){
    this.quantity * this.getNetPrice()
  }

}