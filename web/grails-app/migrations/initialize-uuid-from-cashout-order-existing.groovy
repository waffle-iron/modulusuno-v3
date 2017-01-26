import com.modulus.uno.CashOutOrder

databaseChangeLog = {
  changeSet(author: "temoc (manual)", id: "initialize-uuid-to-cashout-order") {
    grailsChange {
      change {
        CashOutOrder.list().each {
          it.uuid = UUID.randomUUID().toString().replace('-','')[0..15]
          it.save()
        }
      }
    }
  }
}

