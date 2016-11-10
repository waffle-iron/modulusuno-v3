package com.modulus.uno

class Payment {

  BigDecimal amount
  PaymentStatus status = PaymentStatus.PENDING
  SaleOrder saleOrder

  Date dateCreated
  Date lastUpdated

  static belongsTo = [company: Company]

  static constraints = {
    amount nullable:false
    saleOrder nullable:true
  }
}
