import com.modulus.uno.FeesReceipt

databaseChangeLog = {
  changeSet(author: "temoc (manual)", id: "initialize-uuid-for-existing-fees-receipt") {
    grailsChange {
      change {
        FeesReceipt.list().each {
          it.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
          it.save()
        }
      }
    }
  }
}

