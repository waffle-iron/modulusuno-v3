package com.modulus.uno

import spock.lang.Specification
import grails.test.mixin.TestFor
import grails.test.mixin.Mock

@TestFor(StpDepositService)
@Mock([StpDeposit])
class StpDepositServiceSpec extends Specification {

  void "Should save a notification from stp deposit"(){
    given:"A string notification with xml"
      String notification = """<Abono>
      <Clave>1101</Clave>
      <FechaOperacion>20100323</FechaOperacion>
      <InstitucionOrdenante clave="846"/>
      <InstitucionBeneficiaria clave="90646"/>
      <ClaveRastreo>GEM801</ClaveRastreo>
      <Monto>200</Monto>
      <NombreBeneficiario>Techminds</NombreBeneficiario>
      <TipoCuentaBeneficiario clave="40"/>
      <CuentaBeneficiario>646118011900010001</CuentaBeneficiario>
      <RfcCurpBeneficiario>RFC121212ABC</RfcCurpBeneficiario>
      <ConceptoPago>prueba concepto</ConceptoPago>
      <ReferenciaNumerica>2</ReferenciaNumerica>
      <Empresa>STP</Empresa>
      </Abono>
      """
    when:"We save the notification"
      def stpDeposit = service.notificationDepositFromStp(notification)
    then:"We validate"
      stpDeposit.id > 0
      stpDeposit.tracingKey == "GEM801"
      stpDeposit.amount == new BigDecimal(200)
  }

  void "Should don't save a notification from stp deposit when missing parameters"(){
    given:"A string notification with xml"
      String notification = """<Abono>
      <Clave>1101</Clave>
      <FechaOperacion>20100323</FechaOperacion>
      <InstitucionOrdenante clave="846"/>
      <InstitucionBeneficiaria clave="90646"/>
      <ClaveRastreo>GEM801</ClaveRastreo>
      <NombreBeneficiario>Techminds</NombreBeneficiario>
      <TipoCuentaBeneficiario clave="40"/>
      <CuentaBeneficiario>646118011900010001</CuentaBeneficiario>
      <RfcCurpBeneficiario>RFC121212ABC</RfcCurpBeneficiario>
      <ConceptoPago>prueba concepto</ConceptoPago>
      <ReferenciaNumerica>2</ReferenciaNumerica>
      <Empresa>STP</Empresa>
      </Abono>
      """
    when:"We save the notification"
      def stpDeposit = service.notificationDepositFromStp(notification)
    then:"We expect exception"
      thrown Exception
  }

}
