<li class="panel">
  <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#ROLE_LEGAL_REPRESENTATIVE">
    <g:message code="role_legal_representative" /><i class="fa fa-caret-down"></i>
  </a>
  <ul class="collapse nav" id="ROLE_LEGAL_REPRESENTATIVE">
    <li class="panel" >
      <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE" data-toggle="collapse" class="accordion-toggle" data-target="#administracion">
        Administración<i class="fa fa-caret-down"></i>
      </a>
      <g:render template="/layouts/administracion" />
    </li>
    <li>
      <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE" data-toggle="collapse" class="accordion-toggle" data-target="#registros">
        Registros <i class="fa fa-caret-down"></i>
      </a>
      <g:render template="/layouts/registros" />
    </li>
    <li>
      <a href="javascript:;" data-parent="#ROLE_LEGAL_REPRESENTATIVE" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones-representante">
        Operaciones <i class="fa fa-caret-down"></i>
      </a>
      <g:render template="/layouts/operaciones" model="['action':'representante']"/>
    </li>
    <li>
      <g:link controller="dashboard" action="jobs">
        <i class="fa fa-book"></i> Consultas
      </g:link>
    </li>
  </ul>
</li>
