package com.modulus.uno

class PendingAccounts {
  BigDecimal totalCharges
  BigDecimal totalPayments
  BigDecimal totalExpiredCharges
  BigDecimal totalExpiredPayments

  List<SaleOrder> listCharges
  List<PurchaseOrder> listPayments
  Date startDate
  Date endDate
}
