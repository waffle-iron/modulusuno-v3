$("#amountToCapital").change (
    function () {
      $("#amountTotal").val("")
      $("#newBalance").val("")

      if (isNaN($("#amountToCapital").val())) {
        $("#amountTotal").val("No válido")
        $("#newBalance").val("No válido")
        return
      }

      $("#amountTotal").val($("#amountInterest").val()*1 + $("#amountIvaInterest").val()*1 + $("#amountToCapital").val()*1)
      $("#newBalance").val(($("#currentBalance").val()*1 - $("#amountToCapital").val()*1).toFixed(2))

    }
)
