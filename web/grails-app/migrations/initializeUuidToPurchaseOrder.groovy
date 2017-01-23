 import liquibase.statement.core.*
import com.modulus.uno.PurchaseOrder

databaseChangeLog = {
  changeSet(author: "temoc (manual)", id: "initializeUuidToPurchaseOrder") {
    grailsChange {
      change {
        PurchaseOrder.list().each {
          sql.execute("update purchase_order set uuid=${UUID.randomUUID().toString().replace('-','')[0..15]} where id=${it.id}")
        }
      }
    }
  }
}

