import liquibase.statement.core.*
import com.modulus.uno.BusinessEntity

databaseChangeLog = {
  changeSet(author: "temoc (manual)", id: "initializeUuidToBusinessEntity") {
    grailsChange {
      change {
        BusinessEntity.list().each {
          it.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
          it.save(flush:true)
        }
      }
    }
  }
}
