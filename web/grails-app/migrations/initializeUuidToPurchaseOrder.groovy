import liquibase.statement.core.*
import com.modulus.uno.PurchaseOrder

databaseChangeLog = {
  changeSet(author: "temoc (manual)", id: "initializeUuidToPurchaseOrder") {
    grailsChange {
      change {
        PurchaseOrder.withTransaction {
        PurchaseOrder.list().each {
          it.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
          it.save(flush:true)
        }
        }
      }
    }
  }
}

