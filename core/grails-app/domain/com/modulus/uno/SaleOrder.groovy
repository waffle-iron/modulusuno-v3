package com.modulus.uno

import java.math.RoundingMode

class SaleOrder {

  String rfc
  String clientName
  String uuid
  String folio
  Date dateCreated
  Date lastUpdated

  RejectReason rejectReason
  String comments

  SaleOrderStatus status = SaleOrderStatus.CREADA

  Date fechaCobro

  static belongsTo = [company:Company]

  static hasMany = [addresses:Address, items:SaleOrderItem,authorizations:Authorization, documents:S3Asset]

  static constraints = {
    rfc blank:false,size:10..50
    clientName blank:false, size:1..300
    uuid nullable:true
    rejectReason nullable:true
    comments nullable:true
    folio nullable:true
    fechaCobro nullable:true
  }

  BigDecimal getTotalIVA(){
    items*.appliedIVA.sum() ?: 0
  }

  BigDecimal getTotalIEPS(){
    items*.appliedIEPS.sum() ?: 0
  }

  BigDecimal getTotal(){
    getSubtotal() + getTotalIVA() + getTotalIEPS()
  }

  def getSubtotal(){
    items*.amountWithoutTaxes.sum() ?: 0
  }

  String toString(){
    "${clientName} / \$ ${total.setScale(2, RoundingMode.HALF_UP)}"
  }
}
