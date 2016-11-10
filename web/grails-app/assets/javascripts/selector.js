// TODO : Refactor, esto necesita una prueba y una forma más clara de leer
$( "select" ).change(function () {
  var actions = {
    fisica: function(){
      $(".fisica").show()
      $(".moral").hide()
    },
    moral: function(){
      $(".moral").show()
      $(".fisica").hide()
    }
  }
  var str = $("#type").val().toLowerCase()
  actions[str]()
  $("#persona").val(str)
}).change()

$("input[name=clientProviderType]").click(
    function() {
      if ($(this).val()=='EMPLEADO'){
        $("#person").hide()
        $("#website").hide()
        $(".fisica").show()
        $(".empleado").show()
        $(".moral").hide()
        $("select[name=type]").val('FISICA')
      } else {
        $("#person").show()
        $("#website").show()
        $(".empleado").hide()
        if ($("select[name=type]").val()=='MORAL'){
          $(".moral").show()
          $(".fisica").hide()
        }
      }
    }
)

$(function(){
  $(".empleado").hide()
  if($("input[name=clientProviderType]:checked").val() == 'EMPLEADO'){
    $("#person").hide()
    $("#website").hide()
    $(".fisica").show()
    $(".moral").hide()
    $(".empleado").show()
  }
})

