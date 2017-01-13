
<ul class="collapse nav" id="registros-${action}">
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
