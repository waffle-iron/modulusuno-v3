package com.modulus.uno

import grails.test.mixin.TestFor

import spock.lang.Specification
import spock.lang.Unroll

@TestFor(SaleOrderItem)
class SaleOrderItemSpec extends Specification {

  @Unroll
  void """when we have an sku:#sku, name:#name, price:#price, ieps:#ieps and iva:#iva, quantity:#quantity and want to validate we expect result: #result"""() {
  given:"A item"
    def item = new SaleOrderItem()
  and:"A sale order"
    def saleOrder = new SaleOrder(rfc:'rfc', clientName:'clientName')
  when:"We set its values"
    item.sku = sku
    item.name = name
    item.price = price
    item.ieps = ieps
    item.iva = iva
    item.quantity = quantity
    item.unitType = unitType
    item.currencyType = currencyType
    item.saleOrder = saleOrder
  then:"We validate"
    result == item.validate()
  where:"We have following values"
  sku         | name              | quantity | price | ieps  | iva  | unitType          | currencyType      || result
  'H129'      | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129-1'    | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  'h129'      | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  '123'       | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'h12,'      | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'h1'        | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'h'         | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  ''          | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  null        | 'Galgo System 76' | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'G'               | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129'      | ''                | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | null              | 0        | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | 0.01  | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129'      | 'Galgo System 76' | 0        | 0.00  | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129'      | 'Galgo System 76' | 0        | -0.01 | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | -1    | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | 500   | -0.01 | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | 500   | null  | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | 500   | 0     | 0.01 | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129'      | 'Galgo System 76' | 0        | 500   | 0     | 0.00 | UnitType.UNIDADES | CurrencyType.USD  || true
  'H129'      | 'Galgo System 76' | 0        | 500   | 0     | -0.01| UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | 0        | 500   | 0     | null | UnitType.UNIDADES | CurrencyType.USD  || false
  'H129'      | 'Galgo System 76' | -1       | 500   | 0     | 16   | UnitType.UNIDADES | CurrencyType.USD  || false
  }

}
