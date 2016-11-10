<!doctype html>
<html>
  <head>
    <title>Integradora de Emprendimientos Culturales | Servicios Financieros</title>
    <meta name="layout" content="main">
  </head>
  <body>

    <div class="page-title">
    
      <h1>
      <i class="fa fa-tachometer fa-3x"></i>
        Tablero principal
        <small>Administración total</small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>  <a href="index.html">Tablero principal</a>
        </li>
        <li class="active">Bienvenido, puedes empezar por aquí... </li>
      </ol>
    </div>

    <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
    <div class="row">
      <div class="col-md-12">
        <g:if test="${companies.isEmpty()}">
        <div class="alert alert-info">
          <strong>Atención:</strong> Vemos que aún no tienes empresas registradas en la integradora, comienza...
        </div>
        <p>
        <g:link class="btn btn-default btn-lg" controller="company" action="create">
        Registra tu primera empresa
        </g:link>
        </g:if>
        </p>
      </div>
    </div>


    <g:if test="${!companies.isEmpty()}">
    <div align="center">
      <p class="text-primary">Estas son las empresas que tienes disponibles, selecciona una...</p>
    </div>

    <div id="list-company" class=" portlet-blue" role="main">
      <g:render template="/managerApplication/companyList" model="[companies:companies]"/>
    </div>
    </g:if>
    </sec:ifAnyGranted>
    <sec:ifAnyGranted  roles="ROLE_INTEGRADO_AUTORIZADOR">
      <h1>Seccion para Operaciones de la integradora</h1>
    </sec:ifAnyGranted>

  </body>
</html>
