function complete(){
  var clabe = this.value
  var objBanco = null
  if ($("#banco").length)
    objBanco = $("#banco option")
  else
    objBanco = $("#sbanco option")
  objBanco.each(function(){
    if(clabe.substring(0,3) == this.value.substring(this.value.length-3, this.value.length)){
      if ($("#banco").length)
        $("#banco").val(this.value)
      else
        $("#sbanco").val(this.value)
      $("#bank").val(this.value)
      $("#branchNumber").val(clabe.substring(3,6))
      $("#accountNumber").val(clabe.substring(6,17))
    }
  })
}

$(document).ready(function(){
  $('#clabe').on('change', complete)
})

