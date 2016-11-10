<%! import com.modulus.uno.CompanyStatus %>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Integradora de Emprendimientos Culturales | Servicios Financieros</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="main-nav.js"/>

    <g:layoutHead/>
  </head>
  <body>
    <nav class="navbar-top" role="navigation">
      <!-- begin BRAND HEADING -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle pull-right" data-toggle="collapse" data-target=".sidebar-collapse">
          <i class="fa fa-bars"></i> Menu
        </button>
        <div class="navbar-brand">
          <a href="index.html">
            <asset:image src="logo-iecce-blank.png" width="120px" />
          </a>
        </div>
      </div>

      <div class="nav-top">
        <ul class="nav navbar-left">
          <li class="tooltip-sidebar-toggle">
            <a href="#" id="sidebar-toggle" data-toggle="tooltip" data-placement="right" title="Mostrar/ocultar menú">
              <i class="fa fa-bars"></i>
            </a>
          </li>
          <!-- You may add more widgets here using <li> -->
        </ul>
        <ul class="nav navbar-brand">
          <li class="tooltip-sidebar-toggle" align="right">
            <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
            <g:if test="${session.company}">
            <g:form class="form-group" id="company-selection" url="[action:'setCompanyInSession',controller:'company']" >
            <font color="white">Selecciona tu Compañía </font>${companyInfo.selectedCompany()}
            <button type="submit" class="btn btn-primary btn-xs">Entrar</button>
            </g:form>
            </g:if>
            </sec:ifAnyGranted>
          </li>
        </ul>
        <ul class="nav navbar-right">
          <!-- begin USER ACTIONS DROPDOWN -->
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-user"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
              <li>
                <g:link controller="user" action="profile" id="${sec.loggedInUserInfo(field: "id")}">
                <i class="fa fa-user"></i> My Profile
                </g:link>
              </li>
              <li class="divider"></li>
              <li>
                <g:link controller="logout" action="index" class="logout_open">
                <i class="fa fa-sign-out"></i> Cerrar sesión
                </g:link>
              </li>
            </ul>
            <!-- /.dropdown-menu -->
          </li>
          <!-- /.dropdown -->
          <!-- end USER ACTIONS DROPDOWN -->
        </ul>

      </div> <!--ENDOF NAV-TOP -->

    </nav>

    <nav class="navbar-side" role="navigation">
      <div class="navbar-collapse sidebar-collapse collapse">
        <ul id="side" class="nav navbar-nav side-nav">
          <li>
            <g:link id="dashboard" controller="dashboard">
              Tablero principal
            </g:link>
          </li>
          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
          <g:if test="${session.company}">
          <li>
            <g:link id="${session.company}" controller="company" action="show">${companyInfo.companyInfo()}</g:link>
          </li>
          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE, ROLE_INTEGRADO_OPERADOR">
          <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
          <li>
            <g:link id="statement" controller="company" action="accountStatement"><g:message code="Estado de Cuenta" default="Estado de Cuenta" /></g:link>
          </li>
            </g:if>
          </sec:ifAnyGranted>
          </g:if>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li class="panel">
            <a id="integrated" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#integrados">
              Integrados IECCE <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="integrados">
              <li><g:link controller="managerApplication" >Solicitudes de Integrados</g:link></li>
              <li><g:link controller="managerApplication" action="list" >Compañías Integradas</g:link></li>
              <li><g:link controller="managerApplication" action="search">Búsqueda de Integrados</g:link></li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
          <g:if test="${session.company}">
          <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
          <li class="panel">
            <a id="administration" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#administracion">
              Administración <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="administracion"><!---->
              <li><g:link controller="user" action="authorizer">Alta Usuario</g:link></li>
              <li><g:link controller="user" action="manageusers">Lista de Usuarios</g:link></li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </g:if>
          </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
          <li class="panel">
            <a id="integrated" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#empresas_integradas">
              Empresas integradas <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="empresas_integradas">
              <li><g:link controller="company" action="create">Nueva solicitud de integrado</g:link></li>
              <li><g:link controller="company" action="index">Lista de empresas integradas</g:link></li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_OPERADOR">
          <g:if test="${session.company}">
          <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
          <li class="panel">
            <a id="records" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#registros">
              Registros / Altas <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="registros">
              <li>
                <g:link action="create" controller="businessEntity" >Agregar Relación Comercial</g:link>
              </li>
              <li>
                <g:link controller="businessEntity" action="index">Lista de Relaciones Comerciales</g:link>
              </li>
              <li>
                <g:link action="create" controller="product">Agregar un producto/servicio</g:link>
              </li>
              <li>
                <g:link controller="product" action="index">Mis productos/servicios</g:link>
              </li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </g:if>
          </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE, ROLE_INTEGRADO_OPERADOR">
          <g:if test="${session.company}">
          <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
          <li class="panel">
            <a id="operations" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones">
              Operaciones <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="operaciones">
              <li class="panel">
                <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesDeposito">
                  Orden de Depósito <i class="fa fa-caret-down"></i>
                </a>
                <ul class="collapse nav" id="ordenesDeposito">
                  <li>
                    <g:link controller="depositOrder" action="create">Nueva</g:link>
                  </li>
                  <li>
                    <g:link controller="depositOrder" action="list">Listado</g:link>
                  </li>
                </ul>
              </li>
              <li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#cashoutOrder">
                    Orden de Retiro<i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="cashoutOrder">
                    <li>
                      <g:link controller="cashOutOrder" action="create">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="cashOutOrder" action="list">Listado</g:link>
                    </li>
                  </ul>
                </li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#saleOrder">
                    Orden de Facturación<i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="saleOrder">
                    <li>
                      <g:link controller="saleOrder" action="create">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="saleOrder" action="list">Listado</g:link>
                    </li>
                    <li>
                      <g:link controller="payment" action="reconcile">Conciliaciones</g:link>
                    </li>
                  </ul>
                </li>

                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesCompra">
                    Orden de Pago a Proveedor <i class="fa fa-caret-down"></i>
                  </a>

                  <ul class="collapse nav" id="ordenesCompra">
                    <li>
                      <g:link controller="purchaseOrder" action="create">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="purchaseOrder" action="list">Listado</g:link>
                    </li>
                  </ul>
                </li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesReembolso">
                    Orden de Reembolso <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="ordenesReembolso">
                    <li>
                      <g:link controller="purchaseOrder" action="createMoneyBackOrder">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="purchaseOrder" action="listMoneyBackOrders">Listado</g:link>
                    </li>
                  </ul>
                </li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#prestamos">
                    Orden de Préstamo <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="prestamos">
                    <li>
                      <g:link controller="loanOrder" action="create">
                      Nueva
                      </g:link>
                    </li>
                    <li>
                      <g:link controller="loanOrder" action="list">
                      Listado
                      </g:link>
                    </li>
                    <li>
                      <g:link controller="loanOrder" action="listApproved">
                      Préstamos aprobados
                      </g:link>
                    </li>
                  </ul>
                </li>

                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#pagos">
                    Pagos a Préstamos <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="pagos">
                    <li>
                      <g:link controller="loanPaymentOrder" action="chooseLoan">
                      Nuevo Pago a Préstamo
                      </g:link>
                    </li>
                    <li>
                      <g:link controller="loanPaymentOrder" action="chooseLoan" params="[payments:true]">
                      Lista de Pagos
                      </g:link>
                    </li>
                  </ul>
                </li>

                <li class="panel">
                  <a href="javascript:;" data-parent="#consultas" data-toggle="collapse" class="accordion-toggle" data-target="#feesReceipt">
                    Recibo de Honorarios<i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="feesReceipt">
                    <li>
                      <g:link controller="feesReceipt" action="list">
                      Todas</a>
                      </g:link>
                    </li>
                    <li>
                      <g:link controller="feesReceipt" action="list" params="[status:'CREADA']">
                      Creadas
                      </g:link>
                    </li>
                  </ul>
                </li>
            </ul>
              </li> <!--ENDOF LI.PANEL -->
          </g:if>
          </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li>
            <a id="inquiry" href="${createLink(controller:'dashboard', action:'iecce')}">
              Consultas IECCE
            </a>
          </li>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li class="panel">
            <a id="accounts" href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#cuentas">
              Cuentas <i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="cuentas">
              <li>
                <g:link controller="managerApplication" action="accountStatementISR" >ISR</g:link>
              </li>
              <li>
                <g:link controller="managerApplication" action="accountStatementIVA" >IVA</g:link>
              </li>
              <li>
                <g:link controller="managerApplication" action="accountStatementFEE" >Comisiones</g:link>
              </li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
          <li class="panel">
            <a class="" href="${createLink(controller:'dashboard', action:'authorizations')}">
              Autorizaciones
            </a>
          </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>

          <li><g:link id="closeSession" controller="logout" action="index">Cerrar sesión</g:link></li>

        </ul>
      </div>
    </nav> <!--ENDOF NAVBAR-SIDE -->

    <div id="page-wrapper">
      <div class="page-content page-content-ease-in">
        <div class="row">
          <div class="col-lg-12">
            <g:layoutBody/>

          </div>
        </div>
      </div>
    </div><!-- ENDOF PAGE-WRAPPER -->


    <div class="footer" role="contentinfo"></div>
    <div id="spinner" class="spinner" style="display:none;">
      <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

  </body>
</html>
