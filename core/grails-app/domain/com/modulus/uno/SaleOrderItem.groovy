package com.modulus.uno

class SaleOrderItem {

  String sku
  String name
  Integer quantity = 1
  BigDecimal price = new BigDecimal(0)
  BigDecimal ieps = new BigDecimal(0)
  BigDecimal iva = new BigDecimal(0)

  UnitType unitType
  CurrencyType currencyType = CurrencyType.PESOS

  static belongsTo = [saleOrder:SaleOrder]

  static constraints = {
    sku blank:false,size:4..50,matches:/^[A-Za-z0-9-]*$/
    name blank:false,size:1..200
    price min:0.0,max:250000000.00
    ieps min:0.0,max:100.00
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