import com.modulus.uno.SaleOrder

databaseChangeLog = {

  changeSet(author: "temoc (manual)", id: "moving-uuid-to-folio-from-sale-order") {
    grailsChange {
      change {
        SaleOrder.list().each {
          it.folio = it.uuid
          it.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
          it.save()
        }
      }
    }
  }

}
