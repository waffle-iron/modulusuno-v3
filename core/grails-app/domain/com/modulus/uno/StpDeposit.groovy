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
    operationNumber nullable:false
    operationDate nullable:false
    payerKey blank:false
    beneficiaryKey blank:false
    tracingKey blank:false
    amount nullable:false
    payerName blank:true, nullable:true
    beneficiaryName blank:false
    typeAccountBeneficiary nullable:false
    accountBeneficiary blank:false
    rfcCurpBeneficiary blank:false
    paymentConcept blank:false
    numericalReference nullable:false
    companyNameStp blank:false
  }

}
