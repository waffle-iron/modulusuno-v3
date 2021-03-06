<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <link href="carousel.css" rel="stylesheet">
    <title>Modulus UNO</title>
    <style>
      .carousel .item {
        background-color: #E0E0E0!important;
      }
    </style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <ul class="nav navbar-nav">
            <sec:ifNotLoggedIn>

              <li><g:link controller="dashboard" >Ingresa a tu Cuenta</g:link></li>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
              <li><g:link controller="dashboard">Entra al dashboard</g:link></li>
            </sec:ifLoggedIn>
          </ul>
        </div>
      </div>
    </nav>

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <div class="container">
            <br />
            <div class="carousel-caption">
              <asset:image width="40%" src="Logo-ModulusUno-vFINAL-big.png"/>
            </div>
          </div>
        </div>
    </div>
      <br />

    <div class="container marketing">
      <div class="row">
        <div class="col-lg-4">
          <asset:image width="25%" class="img-circle" src="Logosimbolo-ModulusUno-vFINAL-big.png"/>
          <h2>REGISTRA</h2>
          <p>Registra a todos tus clientes, proveedores, empleados, colaboradores, productos o inventarios.
          Lleva fácilmente su control.</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <asset:image width="25%" class="img-circle" src="Logosimbolo-ModulusUno-vFINAL-big.png"/>
          <h2>OPERA</h2>
          <p>Realiza depósitos, retiros o pagos. Recibe cobranza, actualiza inventarios.
          Todo de una manera muy sencilla.</p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <asset:image width="25%" class="img-circle" src="Logosimbolo-ModulusUno-vFINAL-big.png"/>
          <h2>CONSULTA</h2>
          <p>Cuanto debo? Cuanto me deben? Que hay que pagar esta semana?
          Tan fácil como consultarlo.</p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->

      <hr class="featurette-divider">
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
        <p>&copy; 2015 Company, Inc. &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
      </footer>
    </div>
  </body>
</html>
