package com.modulus.uno

class DashboardController {

  def companyService
  def springSecurityService

  def index() {
    def user = springSecurityService.currentUser
    def companyList = companyService.allCompaniesByUser(user)
    [companies:companyList, companiesCount: companyList.size()]
  }

  def authorizations() {
    Company company = Company.get(session.company)
    render view:'iecce', model:[
      depositOrderToAuthorizeCount : DepositOrder.countByStatusAndCompany(DepositOrderStatus.VALIDATE, company),
      cashOutOrderToAuthorizeCount : CashOutOrder.countByStatusAndCompany(CashOutOrderStatus.TO_AUTHORIZED, company),
      saleOrderToAuthorizeCount : SaleOrder.countByStatusAndCompany(SaleOrderStatus.POR_AUTORIZAR, company),
      saleOrderToCancelBillForAuthorizeCount : SaleOrder.countByStatusAndCompany(SaleOrderStatus.CANCELACION_POR_AUTORIZAR, company),
      purchaseOrderToAuthorizeCount : PurchaseOrder.countByStatusAndIsMoneyBackOrderAndCompany(PurchaseOrderStatus.POR_AUTORIZAR, false, company),
      moneyBackOrderToAuthorizeCount : PurchaseOrder.countByStatusAndIsMoneyBackOrderAndCompany(PurchaseOrderStatus.POR_AUTORIZAR, true, company),
      loanOrderToAuthorizeCount : LoanOrder.countByStatusAndCompany(LoanOrderStatus.VALIDATE, company),
      feesReceiptToAuthorizeCount : FeesReceipt.countByStatusAndCompany(FeesReceiptStatus.POR_AUTORIZAR, company),
      loanPaymentOrderToAuthorizeCount : LoanPaymentOrder.countByStatusAndCompany(LoanPaymentOrderStatus.VALIDATE, company)
    ]
  }
}
