
 Dropzone.options.myAwesomeDropzone = {
  //paramName: "file",
  //autoProcessQueue: false,
  uploadMultiple: true,
  parallelUploads: 1,
  maxFiles: 1,
  maxFilesize: 10, //MB
  addRemoveLinks: true,
  //previewsContainer: ".dropzone-previews",
  dictRemoveFile: "Eliminar",
  dictCancelUpload: "Cancel",
  dictDefaultMessage: "Arrastra la factura aqui",
  dictFileTooBig: "Archivo demasiado grande: 10 MB",
  dictMaxFilesExceeded: "Solo se puede enviar una factura",
  //acceptedFiles: ".xml",

  init: function() {
    var myDropzone = this;

    $("#cancel").hide();
    $(".confirm-btn").hide();

    myDropzone.on("addedfile", function (file) {
      console.log(file)
    });

    myDropzone.on("maxfilesexceeded", function(file){
      alert("Solo se puede enviar un archivo");
      myDropzone.removeFile(file)
    });

    myDropzone.on('sending', function(file, xhr, formData) {
      console.log(file, xhr, formData)
    });

    myDropzone.on('success', function(file, xhr, formData) {
      $("#cancel").show();
      $("#my-awesome-dropzone").hide();
      var source   = $("#invoice-template").html();
      var template = Handlebars.compile(source);
      var html    = template(xhr.inf[0]);
      $("#result").html(html);
      if (xhr.errors){
        renderTemplateInfo(xhr);
      } else if (xhr.bank) {
        renderBankAccountNotExist(xhr)
      } else {
        $(".confirm-btn").show();
      }

    });

    myDropzone.on("complete", function (file, xhr, formData) {
      console.log(file)
    });

    $("#cancel").click(function (e) {
        $("#cancel").hide();
        $(".confirm-btn").hide();
        $(".message-provider-bank-account-not-exist").remove();
        $("#my-awesome-dropzone").show();
        e.preventDefault();
        e.stopPropagation();
        myDropzone.removeAllFiles();
    });
  }
}

function renderTemplateInfo(xhr){
  var sourceInfo = $("#exist-provider").html();
  var templateInfo = Handlebars.compile(sourceInfo);
  var htmlInfo    = templateInfo(xhr);
  $("#resultInfo").html(htmlInfo)
}

function renderBankAccountNotExist(xhr) {
  var source = $("#error-bank-account").html();
  var template = Handlebars.compile(source)
  var html = template(xhr)
  $("#error-display-bank-account").html(html);
}


