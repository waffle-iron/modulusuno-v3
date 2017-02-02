databaseChangeLog = {
  include file: 'initial-structure.groovy'
  include file: 'create-m1-roles.groovy'
  include file: 'adding-user-admin-production.groovy'
  include file: 'adding-list-banks.groovy'
  include file: 'adding-colunm-reference-to-payment.groovy'
  include file: 'adding-uuid-to-purchase-order.groovy'
  include file: 'initializeUuidToPurchaseOrder.groovy'
    include file: 'adding-unique-to-variable-of-corporate.groovy'
    include file: 'updating-type-venta-to-factura-in-commission.groovy'
}
