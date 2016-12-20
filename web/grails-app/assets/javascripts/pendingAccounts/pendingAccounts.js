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
    modal.find('#chargeId').val(recipient)
});

$('#changeDatePaymentModal').on('show.bs.modal', function (event) {
  var button = $(event.relatedTarget)
    var recipient = button.data('whatever')
    var modal = $(this)
    modal.find('#paymentId').val(recipient)
});

$( function() {
  $( "#startDate" ).datepicker({
    dateFormat: 'dd/mm/yy',
    minDate: 0
  });
} );

$( function() {
  $( "#endDate" ).datepicker({
    dateFormat: 'dd/mm/yy',
    minDate: 0
  });
} );

