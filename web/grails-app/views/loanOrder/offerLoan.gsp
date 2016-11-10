<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanOrder.label', default: 'LoanOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>Ofertar un préstamo</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
        <li class="active">Préstamos</li>
      </ol>
    </div>
    <div class="row">

    <div class="col-lg-12">
      <g:form controller="loanOrder" action="createOfferLoan">
      <div class="portlet portlet-default">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Datos del préstamo a ofrecer</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
            <div class="form-inline">
              <div class="form-group">
                <label class="sr-only" for="inputAmount">Monto</label>
                <div class="input-group">
                  <div class="input-group-addon input-lg">$</div>
                  <input type="number" class="form-control input-lg" id="amount" name="amount" placeholder="Monto" required="" pattern="[0-9]+(\.[0-9]{2})?" title="Ingrese una cantidad en formato correcto: sin separador de miles, sin decimales, o bien, con dos decimales" />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="portlet-footer">
          <button type="submit" class="btn btn-default">
            Ofertar el préstamo
          </button>
        </div>
      </div>
      </g:form>
    </div>

    </div>
  </body>
</html>
