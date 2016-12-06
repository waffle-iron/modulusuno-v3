package com.modulus.uno

class CashFlow {
  BigDecimal totalCharges
  BigDecimal totalPayments
  List<SaleOrder> listCharges
  List<PurchaseOrder> listPayments
  Date startDate
  Date endDate
}
