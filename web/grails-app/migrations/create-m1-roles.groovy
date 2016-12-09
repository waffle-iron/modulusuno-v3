import com.modulus.uno.Role

databaseChangeLog = {

  changeSet(author: "neodevelop (manual)", id: "new-roles-for-m1") {
    grailsChange {
      change {
        [
          "ROLE_M1",
          "ROLE_CORPORATIVE",
          "ROLE_LEGAL_REPRESENTATIVE",
          "ROLE_FICO",
          "ROLE_AUTHORIZER",
          "ROLE_OPERATOR",
          "ROLE_VISOR"
        ].each { roleName ->
          new Role(roleName).save(flush:true)
        }
      }
    }
  }

}
