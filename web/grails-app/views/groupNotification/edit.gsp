<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title></title>
  </head>
  <body>

<!-- BEGIN PAGE TITLE -->
    <div class="page-title">

      <h1>
        <i class="fa fa-user fa-3x"></i>
        Registro de grupo de usuarios a notificar
        <small>Nuevo Grupo</small>
      </h1>

    </div>

    <div class="row">

      <div class="portlet portlet-blue">
        <div class="portlet-heading">
        <!-- BEGIN PORTLET-WIDGETS -->
        <div class="portlet-widgets">
      </div>
      <!-- END OF PORTLET-WIDGETS -->
      <div class="clearfix"></div>
      </div>

      <!-- BEGIN BLUE-PORTLET -->
      <div class="panel-collapse collapse in">
        <!-- BEGIN PORTLET-BODY -->
        <div class="portlet-body">
          <g:form controller="groupNotification" action="update">


           <div class="form-group">
            <div class="input-group col-md-4">
              <label for="">Grupo a Actualizar: ${group.name}</label>
            </div>
           </div>

           <div class="form-group">
           <label for="">Actualizar Emailer para notificar</label>
            <div class="input-group col-md-4">
            <g:select optionKey="id" optionValue="subject"
                  name="notificationId" from="${emailers}" />
            </div>
           </div>

           <div class="form-group">
           <label for="">Actualizar Usuarios que serán notificados</label>
            <div class="input-group col-md-4">
               <g:each var="user" in="${users}">
               <g:checkBox name="userList" value="${user.id}" checked="false" />
                  ${user.username}
                  <br>
                  </g:each>
            </div>
           </div>

          <div class="form-group">
            <div class="input-group col-md-4">
             <g:actionSubmit class="save btn btn-default" value="Save"/>
          </g:form>
            </div>
          </div>

        </div>
      </div>
      </div>
  </body>
</html>
