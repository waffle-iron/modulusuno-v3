package com.modulus.uno

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import java.lang.Void as Should
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(BusinessEntityService)
@Mock([BusinessEntity])
class BusinessEntityServiceSpec extends Specification {

  def names = []
  def bankAccounts = []
  def addresses = []

  def bankAccountService = Mock(BankAccountService)

  def setup() {
    names.removeAll()
    bankAccounts.removeAll()
    addresses.removeAll()
    service.bankAccountService = bankAccountService
  }

  @Unroll
  void "Should append names to business entity for properties #properties"() {
    given:"a business entity"
      def name = new ComposeName(value:"Nombre", type:NameType.NOMBRE)
      def businessEntity = Mock(BusinessEntity)
      businessEntity.metaClass.addToNames = {
        names.add(name)
      }
    when:""
      def result = service.appendNamesToBusinessEntity(businessEntity, properties)
    then:""
      names.size() == sizeExpected
      1 * businessEntity.save()
    where:
      properties                                      || sizeExpected
      ["Nombre", "Paterno", "Materno"] as String[]    ||  3
      ["Nombre", "Paterno", "Materno", "NoEmpleado"] as String[]  ||  4
  }

  void "Should append data to business entity"() {
    given:"a business entity"
      def name = new ComposeName(value:"Negocio", type:NameType.RAZON_SOCIAL)
      def businessEntity = Mock(BusinessEntity)
      businessEntity.metaClass.addToNames = {
        names.add(name)
      }
    when:""
      def result = service.appendDataToBusinessEntity(businessEntity, "Negocio")
    then:""
      names.size() == 1
      1 * businessEntity.save()
  }

  void "Should create a bank account and adding to business entity"() {
    given:"A business entity and a bank account"
      def bankAccount = Mock(BankAccount)
      def businessEntity = new BusinessEntity()
      businessEntity.metaClass.addToBanksAccounts = {
        bankAccounts.add(bankAccount)
      }
    and:
      bankAccountService.createABankAccount(_) >> bankAccount
    when:
      def result = service.createBankAccountAndAddToBusinesEntity([:], businessEntity)
    then:
      1 * bankAccount.save()
      bankAccounts.size() == 1
  }

  void "Should add an address to business entity"() {
    given:"A business entity and an address"
      def address = Mock(Address)
      def businessEntity = new BusinessEntity(rfc:"AAA010101AAA")
      businessEntity.metaClass.addToAddresses = {
        addresses.add(address)
      }
      BusinessEntity.metaClass.static.get = {Long id -> businessEntity }
    when:
      def result = service.createAddressForBusinessEntity(address, 1)
    then:
      addresses.size() == 1
  }

}
