var BusinessEntityController = (function(){

  var regimenType = '#regimenBusiness',
      entityRadio = 'input[name="clientProviderType"]',
      regimenSelected = '#type option[value="MORAL"]'

  var start = function(option) {
    newProviderByInvoice();
  };

  var newProviderByInvoice = function() {
    var type = $(regimenType).val();
    if (type != "") {
      $(entityRadio).filter('[value="PROVEEDOR"]').attr('checked', true);
    }
    if (type == "MORAL") {
      $(regimenSelected).prop('selected', true);
      $(regimenSelected).change();
    }
  };

  return {
    start:start
  };

}());
