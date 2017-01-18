package com.modulus.uno

class DashboardController {

  def companyService
  def springSecurityService
  OrganizationService organizationService

  def index() {
    def user = springSecurityService.currentUser
    def companyList

    if (session.corporate)
      companyList = companyService.findCompaniesByCorporateAndStatus(CompanyStatus.ACCEPTED,session.corporate.id)
    else
      companyList = organizationService.findAllCompaniesOfUser(user)

    def corporates= Corporate.list()

    [companies:companyList,
     corporates:corporates,
     companiesCount: companyList.size(),
     user:user]
  }

  def jobs() {
    [
    depositOrderAuthorizedCount : DepositOrder.countByStatus(DepositOrderStatus.AUTHORIZED),
    cashOutOrderAuthorizedCount : CashOutOrder.countByStatus(CashOutOrderStatus.AUTHORIZED),
    saleOrderAuthorizedCount : SaleOrder.countByStatus(SaleOrderStatus.AUTORIZADA),
    saleOrderToCancelBillForExecuteCount : SaleOrder.countByStatus(SaleOrderStatus.CANCELACION_AUTORIZADA),
    purchaseOrderAuthorizedCount : PurchaseOrder.countByStatusAndIsMoneyBackOrder(PurchaseOrderStatus.AUTORIZADA, false),
    moneyBackOrderAuthorizedCount : PurchaseOrder.countByStatusAndIsMoneyBackOrder(PurchaseOrderStatus.AUTORIZADA, true),
    loanOrderAuthorizeCount : LoanOrder.countByStatus(LoanOrderStatus.AUTHORIZED),
    loanOrderToExecuteCount : LoanOrder.countByStatus(LoanOrderStatus.ACCEPTED),
    feesReceiptCount : FeesReceipt.countByStatus(FeesReceiptStatus.AUTORIZADA),
    paymentsToConciliateCount: Payment.countByStatus(PaymentStatus.PENDING),
    loanPaymentOrderAuthorizedCount : LoanPaymentOrder.countByStatus(LoanPaymentOrderStatus.AUTHORIZED)
    ]
  }

  def authorizations() {
    Company company = Company.get(session.company)
    render view:'jobs', model:[
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

  def listCompanies() {
     params.max = (params.max ?: 10)
     [companies:Company.list(params),companiesCount: Company.count()]
  }

}
