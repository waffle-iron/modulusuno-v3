<!doctype html>
<html>
  <head>
    <title>Integradora de Emprendimientos Culturales | Servicios Financieros</title>
  </head>
  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
      </div>
      <div id="navbar" class="navbar-collapse collapse">
      </div><!--/.navbar-collapse -->
    </div>
    </nav>

    <div class="jumbotron bg">
      <div class="container">
        <h1>Bienvenido a IECCE!</h1>
        <p>
        <sec:ifNotLoggedIn>
        <g:link controller="user" action="create"><button type="button" class="btn btn-primary btn-lg">Solicita Aquí tu Registro</button></g:link>
        <g:link controller="dashboard" class="btn btn-default btn-lg">Ingresa a tu Cuenta</g:link>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
        <g:link controller="dashboard" class="btn btn-primary">Entra al dashboard</g:link>
        </sec:ifLoggedIn>
        </p>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="col-md-4">
            <div class="col-md-12">
              <div align="center">
                <asset:image src="acerca-iecce.png" width="30%" />
              </div>
              <div >
                <h2 align="center">Acerca de IECCE</h2><br/>
                <h3>Integradora de Emprendimientos Culturales</h3>
                <br/>
                <p>
                Somos más que un proveedor de servicios integrales, ya que nos interesa crear sinergías y alianzas de largo plazo con nuestros clientes y socios de negocio.
                </p>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="col-md-12">
              <div align="center">
                <asset:image src="beneficios-iecce.png" width="30%" />
              </div>
              <div >
                <h2 align="center">Beneficios</h2><br/>
                <h3>Realmente vamos más allá.</h3>
                <br/>
                <p>
                IECCE te ofrece servicios administrativos y financieros en esquemas comerciales muy accesibles, lo que podría significar no solo grandes ahorros, si no decirle adiós a muchos dolores de cabeza innecesarios.
                </p>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="col-md-12">
              <div align="center">
                <asset:image src="servicios-iecce.png" width="30%" />
              </div>
              <div >
                <h2 align="center">Servicios</h2><br/>
                <h3>¿Quieres relajarte y disfrutar tu negocio?</h3>
                <br/>
                <p>
                Tenemos una solución a la medida de tus necesidades, que sin duda te ayudará a concentrarte en lo que a ti más te gusta, mientras dejas el trabajo pesado en nuestras manos.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
