$( function() {
  $( "#datepickerCharge" ).datepicker({
    dateFormat: 'dd/mm/yy'
  });
} );

$( function() {
  $( "#datepickerPayment" ).datepicker({
    dateFormat: 'dd/mm/yy',
    minDate: 0
  });
} );

$('#changeDateChargeModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget)
    var recipient = button.data('whatever')
    var modal = $(this)
    modal.find('#orderId').val(recipient)
});

