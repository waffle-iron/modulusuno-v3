<%! import com.modulus.uno.CompanyStatus %>
<%! import com.modulus.uno.CompanyTaxRegime %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Modulus UNO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="main-nav.js"/>
    <g:layoutHead/>
  </head>
  <body>
    <nav class="navbar-top" role="navigation">
      <div class="navbar-header">
        <br /><font color="white" size="4">MODULUS UNO</font>
        <button type="button" class="navbar-toggle pull-right" data-toggle="collapse" data-target=".sidebar-collapse">
          <i class="fa fa-bars"></i> Menu
        </button>
        <div class="navbar-brand">
        </div>
      </div>

      <div class="nav-top">
        <ul class="nav navbar-left">
          <li class="tooltip-sidebar-toggle">
            <a href="#" id="sidebar-toggle" data-toggle="tooltip" data-placement="right" title="Mostrar/ocultar menú">
              <i class="fa fa-bars"></i>
            </a>
          </li>
        </ul>
        <ul class="nav navbar-brand">
          <li class="tooltip-sidebar-toggle" align="right">
            <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_VISOR,ROLE_LEGAL_REPRESENTATIVE_EJECUTOR,ROLE_FICO_VISOR,ROLE_FICO_EJECUTOR,ROLE_AUTHORIZER_VISOR,ROLE_AUTHORIZER_EJECUTOR,ROLE_OPERATOR_VISOR,ROLE_OPERATOR_EJECUTOR">
              <g:if test="${session.company}">
                <g:form class="form-group" id="company-selection" url="[action:'setCompanyInSession',controller:'company']" >
                  <font color="white">Selecciona tu Compañía </font>${companyInfo.selectedCompany()}
                  <input type="submit" class="btn btn-primary btn-xs" />
                </g:form>
              </g:if>
            </sec:ifAnyGranted>

            <!-- TODO esto se quitara cuando desaparescan estos roles -->
            <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
            <g:if test="${session.company}">
            <g:form class="form-group" id="company-selection" url="[action:'setCompanyInSession',controller:'company']" >
            <font color="white">Selecciona tu Compañía </font>${companyInfo.selectedCompany()}
            <input type="submit" class="btn btn-primary btn-xs" />
            </g:form>
            </g:if>
            </sec:ifAnyGranted>

          </li>
          <li>
          &nbsp;
          &nbsp;
          &nbsp;
          &nbsp;
          </li>
          <li align="center">
            <sec:ifLoggedIn>
              <font color="white"> Usuario Logueado: ${modulusuno.userLoggin()}</font>
            </sec:ifLoggedIn>
          </li>
        </ul>
        <ul class="nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-user"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
              <li>
                <g:link controller="user" action="profile" id="${sec.loggedInUserInfo(field: "id")}">
                <i class="fa fa-user"></i> Mi Perfil
                </g:link>
              </li>
              <li class="divider"></li>
              <li>
                <g:link controller="logout" action="index" class="logout_open">
                <i class="fa fa-sign-out"></i> Cerrar sesión
                </g:link>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>

    <nav class="navbar-side" role="navigation">
      <div class="navbar-collapse sidebar-collapse collapse">
        <ul id="side" class="nav navbar-nav side-nav">
          <sec:ifAnyGranted roles="ROLE_M1">
            <li>
              <g:link controller="dashboard" action="index" >Ver Corporativos</g:link>
              <g:link controller="corporate" action="create" >Crear Nuevo Corporativo</g:link>
            </li>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_CORPORATIVE">
            <g:if test="${session.corporate}">
              <li><g:link controller="corporate" action="addCompany" id="${session.corporate.id}">Crear Nueva Empresa</g:link></li>
              <li><g:link controller="corporate" action="addUser" id="${session.corporate.id}">Alta Usuario</g:link></li>
              <li><g:link controller="corporate" action="users" id="${session.corporate.id}">Lista de Usuarios</g:link></li>
              <li><g:link controller="corporate" action="companies" id="${session.corporate.id}">Todas las Empresas</g:link></li>
            </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_VISOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_LEGAL_REPRESENTATIVE_VISOR">
                <g:message code="role_legal_representative_visor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_LEGAL_REPRESENTATIVE_VISOR">
                <li class="panel" >
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_VISOR" data-toggle="collapse" class="accordion-toggle" data-target="#administracion-visor">
                    Administración<i class="fa fa-caret-down"></i>
                  </a>
                  <g:render template="/layouts/administracion" model="['action': 'visor']" />
                </li>
                <li>
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_VISOR" data-toggle="collapse" class="accordion-toggle" data-target="#registros-visor">
                    Registros <i class="fa fa-caret-down"></i>
                  </a>
                  <g:render template="/layouts/registros" model="['action': 'visor']" />
                </li>
                <li>
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_VISOR" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones-visor">
                    Operaciones <i class="fa fa-caret-down"></i>
                  </a>
                  <g:render template="/layouts/operaciones" model="['action': 'visor']" />
                </li>
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
                <g:message code="role_legal_representative_ejecutor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_LEGAL_REPRESENTATIVE_EJECUTOR">
                <li>
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_EJECUTOR" data-toggle="collapse" class="accordion-toggle" data-target="#administracion-ejecutor">
                    Administración<i class="fa fa-caret-down"></i>
                   </a>
                 <g:render template="/layouts/administracion" model="['action': 'ejecutor']" />
                </li>
                <li>
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_EJECUTOR" data-toggle="collapse" class="accordion-toggle" data-target="#registros-ejecutor">
                    Registros <i class="fa fa-caret-down"></i>
                  </a>
                  <g:render template="/layouts/registros" model="['action': 'ejecutor']" />
                </li>
                <li>
                  <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE_EJECUTOR" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones-ejecutor">
                    Operaciones <i class="fa fa-caret-down"></i>
                  </a>
                  <g:render template="/layouts/operaciones" model="['action': 'ejecutor']" />
                <li>
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_FICO_VISOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_FICO_VISOR">
                <g:message code="role_fico_visor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_FICO_VISOR">
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_FICO_EJECUTOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_FICO_EJECUTOR">
                <g:message code="role_fico_ejecutor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_FICO_EJECUTOR">
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_AUTHORIZER_VISOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_AUTHORIZER_VISOR">
                <g:message code="role_authorizer_visor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_AUTHORIZER_VISOR">
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_AUTHORIZER_EJECUTOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_AUTHORIZER_EJECUTOR">
              <g:message code="role_authorizer_ejecutor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_AUTHORIZER_EJECUTOR">
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_OPERATOR_VISOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_OPERATOR_VISOR">
              <g:message code="role_operator_visor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_OPERATOR_VISOR">
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_OPERATOR_EJECUTOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_OPERATOR_EJECUTOR">
              <g:message code="role_operator_ejecutor" /><i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="ROLE_OPERATOR_EJECUTOR">
              </ul>
            </li>
          </sec:ifAnyGranted>

          <g:if test="${session.company && companyInfo.isAvailableForOperationInThisCompany()}">
          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#administracion">
                Administración<i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="administracion">
                <li>
                  <g:link controller="company" action="show" id="${session.company}">Mi Empresa</g:link>
                </li>
                <li>
                  <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == 'true'}">
                    <g:link controller="company" action="accountStatement">Estado de Cuenta</g:link>
                  </g:if>
                </li>
                <li>
                  <g:link controller="user" action="authorizer">Alta Usuario</g:link>
                </li>
                <li>
                  <g:link controller="user" action="edit" id="${sec.loggedInUserInfo(field: "id")}">Editar Usuario</g:link>
                </li>
                <li>
                  <g:link controller="user" action="manageUsers">Lista de Usuarios</g:link>
                </li>

              </ul>
            </li>
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#registros">
                Registros <i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="registros">
                <li>
                  <g:link action="create" controller="businessEntity" params='[clientProviderType:"${com.modulus.uno.LeadType.CLIENTE}"]' >Alta Cliente</g:link>
                </li>
                <li>
                  <g:link action="create" controller="businessEntity"  params='[clientProviderType:"${com.modulus.uno.LeadType.PROVEEDOR}"]'>Alta Proveedor</g:link>
                </li>
                <li>
                  <g:link action="create" controller="businessEntity"  params='[clientProviderType:"${com.modulus.uno.LeadType.EMPLEADO}"]'>Alta Empleado</g:link>
                </li>
                <li>
                  <g:link action="createMultiEmployees" controller="businessEntity" >Alta Multiples Empleados</g:link>
                </li>
                <li>
                  <g:link action="processorPayroll" controller="payroll" >Listado de Archivos procesados</g:link>
                </li>
                <li>
                  <g:link action="create" controller="product">Alta Producto/Servicio</g:link>
                </li>
                <li>
                  <g:link controller="businessEntity" action="index">Lista de Relaciones Comerciales</g:link>
                </li>
                <li>
                  <g:link controller="product" action="index">Mis Productos/Servicios</g:link>
                </li>
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE, ROLE_INTEGRADO_OPERADOR">
          <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == 'true'}">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones">
                Operaciones <i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="operaciones">
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesDeposito">
                    Depósitos <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="ordenesDeposito">
                    <li>
                      <g:link controller="depositOrder" action="create">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="depositOrder" action="list"> Listado</g:link>
                    </li>
                  </ul>
                </li>
                <li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#cashoutOrder">
                    Retiros <i class="fa fa-caret-down"></i>
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
                    Facturas <i class="fa fa-caret-down"></i>
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
                    Órdenes de Compra <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="ordenesCompra">
                    <li>
                      <g:link controller="purchaseOrder" action="create">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="purchaseOrder" action="list"> Listado</g:link>
                    </li>
                  </ul>
                </li>
                <li class="panel">
                  <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesReembolso">
                    Reembolsos a Empleados <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="ordenesReembolso">
                    <li>
                      <g:link controller="purchaseOrder" action="createMoneyBackOrder">Nueva</g:link>
                    </li>
                    <li>
                      <g:link controller="purchaseOrder" action="listMoneyBackOrders"> Listado</g:link>
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
                <li>
                  <g:link controller="movimientosBancarios" >Movimiento</g:link>
                </li>
                <li>
                  <g:link controller="movimientosBancarios" action="multiMovimientos" >Subir Movimientos Bancarios</g:link>
                </li>
              </ul>
            <li>
          </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
            <li class="panel">
              <a class="" href="${createLink(controller:'dashboard', action:'authorizations')}">Autorizaciones</a>
            </li>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_FINANCIAL">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#reportes">
                Reportes<i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="reportes">
                <li><g:link controller="company" action="pendingAccounts">Cuentas por Cobrar/Pagar</g:link></li>
              </ul>
            </li>
          </sec:ifAnyGranted>
          <sec:ifAnyGranted roles="ROLE_EJECUTOR">
            <li>
              <g:link controller="dashboard" action="jobs">
                <i class="fa fa-book"></i> Ejecuciones
              </g:link>
            </li>
          </sec:ifAnyGranted>
         </g:if>

          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li>
            <g:link controller="dashboard" action="jobs">
              <i class="fa fa-book"></i> Consultas
            </g:link>
          </li>
          <li>
            <g:link controller="managerApplication">Todas las Empresas</g:link>
          </li>
          </sec:ifAnyGranted>



          <li><g:link controller="logout" action="index"><i class="fa fa-sign-out"></i> Cerrar sesión</g:link></li>
        </ul>
      </div>
    </nav>

    <div id="page-wrapper">
      <div class="page-content page-content-ease-in">
        <div class="row">
          <div class="col-lg-12">
            <g:layoutBody/>
          </div>
        </div>
      </div>
    </div>

    <div class="footer" role="contentinfo"></div>
    <div id="spinner" class="spinner" style="display:none;">
      <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

  </body>
</html>
