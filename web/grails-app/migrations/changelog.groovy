databaseChangeLog = {
  include file: 'initial-structure.groovy'
  include file: 'create-profile-admin.groovy'
  include file: 'create-profile-integrado.groovy'
  include file: 'create-profile-legal.groovy'
  include file: 'creating-role-admin-iecce.groovy'
  include file: 'creating-role-integrado-autorizador-and-delete-autorizador.groovy'
  include file: 'creating-role-integrado-operador.groovy'
  include file: 'creating-role-operador_iecce.groovy'
  include file: 'rename-http-to-https.groovy'
  include file: 'update-protocol-http-to-https.groovy'
    include file: 'drop-protocol-from-s3asset.groovy'
    include file: 'modify-column-name-from-sale-order-item.groovy'
    include file: 'modify-column-name-from-product.groovy'
    include file: 'adding-column-stpclabe-to-clientlink.groovy'
    include file: 'adding-column-uuid-to-business-entity.groovy'
    include file: 'drop-modulusunoaccounts_and_update_status-for-all-companies.groovy'
 //   include file: 'change-elements-db-new-version.groovy'
    include file: 'adding-ids-of-artimisa.groovy'
  //  include file: 'initializing-uuid-for-existing-business-entity.groovy'
    include file: 'adding-date-operation-in-sale-and-puchase-order.groovy'
}
