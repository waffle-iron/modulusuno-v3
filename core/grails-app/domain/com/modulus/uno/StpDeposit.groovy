package com.modulus.uno

class StpDeposit {
  Long operationNumber
  Date operationDate
  String payerKey
  String beneficiaryKey
  String tracingKey
  BigDecimal amount
  String payerName
  String beneficiaryName
  Long typeAccountBeneficiary
  String accountBeneficiary
  String rfcCurpBeneficiary
  String paymentConcept
  Long numericalReference
  String companyNameStp

  static constraints = {
    operationNumber blank:false
    operationDate blank:false
    payerKey blank:false
    beneficiaryKey blank:false
    tracingKey blank:false
    amount blank:false
    payerName blank:true, nullable:true
    beneficiaryName blank:false
    typeAccountBeneficiary blank:false
    accountBeneficiary blank:false
    rfcCurpBeneficiary blank:false
    paymentConcept blank:false
    numericalReference blank:false
    companyNameStp blank:false
  }

}
