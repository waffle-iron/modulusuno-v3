Handlebars.registerHelper("formatMoney", function(amount){
  
  var locale = 'usa';
  var options = {style: 'currency', currency: 'MXN', minimumFractionDigits: 2, maximumFractionDigits: 2};
  var formatter = new Intl.NumberFormat(locale, options);
  return new Handlebars.SafeString(formatter.format(amount));
});

