var SaleOrderByInvoiceController = (function(){

  var emitterName = '#nombre',
      emitterRfc = '#rfc',
      emitterRegimen = '#regimen',
      providerFormDivContainer = '#resultInfo',
      providerName = 'input[name="name"]',
      providerRfc = 'input[name="rfc"]',
      providerRegimen = 'input[name="regimen"]',
      providerForm = '#new-provider-by-invoice',
      providerBusinesName = 'input[name="businessName"]',
      providerBankAccount = '#newBankAccountProvider',
      providerBankAccountForm = '#new-bank-account-provider-by-invoice',
      emitterRfcBank = 'input[name="rfcBank"]',
      providerBankAccountContainer = '#error-display-bank-account'

  var start = function(option) {
    propagation();
    propagationBankAccount();
  };

  var propagationBankAccount = function() {
    $(providerBankAccountContainer).on('click',providerBankAccount,function(event){
      event.stopPropagation();
      populationNewBankAccount();
      $(providerBankAccountForm).submit();
      return true;
    });
  };

  var propagation = function(){
    $(providerFormDivContainer).on('click','#newProvider',function(event){
      event.stopPropagation();
      populationNewProvider();
      $(providerForm).submit();
      return true;
    });
  };

  var populationNewProvider = function() {
    var regimenValue = $(emitterRegimen).val();
    if (regimenValue.match("Morales")) {
      $(providerRegimen).val("MORAL");
      $(providerBusinesName).val($(emitterName).val());
    } else {
      $(providerRegimen).val("FISICA");
      $(providerName).val($(emitterName).val());
    }
    $(providerRfc).val($(emitterRfc).val());
  };

  var populationNewBankAccount = function() {
    $(emitterRfcBank).val($(emitterRfc).val());
  };

  return {
    start:start
  };

}());
