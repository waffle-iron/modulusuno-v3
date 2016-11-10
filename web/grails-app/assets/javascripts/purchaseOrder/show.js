function calculateAmountAndNetPrice(){
  $("#amount").val("0")
  if (isNaN($("#quantity").val()) || isNaN($("#price").val()) || isNaN($("#ieps").val()) || isNaN($("#iva").val())){
    $("#amount").val("No válido")
    $("#netprice").val("No válido")
    return
  }

  $("#netprice").val(($("#price").val()*(1 + $("#iva").val()/100.00 + $("#ieps").val()/100.00)).toFixed(2))
  $("#amount").val(($("#quantity").val()*$("#netprice").val()).toFixed(2))
}

$("#price").change( function() {
    calculateAmountAndNetPrice()
  }
)

$("#ieps").change( function() {
    calculateAmountAndNetPrice()
  }
)

$("#iva").change( function() {
    calculateAmountAndNetPrice()
  }
)

$("#quantity").change( function() {
   calculateAmountAndNetPrice()
  }
)
