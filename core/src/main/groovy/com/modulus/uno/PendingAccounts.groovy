package com.modulus.uno

class PendingAccounts {
  BigDecimal totalCharges
  BigDecimal totalPayments
  List<SaleOrder> listCharges
  List<PurchaseOrder> listPayments
  Date startDate
  Date endDate
}
