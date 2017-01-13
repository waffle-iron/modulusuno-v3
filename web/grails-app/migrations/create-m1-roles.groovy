import liquibase.statement.core.*
import com.modulus.uno.Role

databaseChangeLog = {

  changeSet(author: "neodevelop (manual)", id: "new-roles-for-m1") {
    grailsChange {
      change {
        ["ROLE_M1",
         "ROLE_CORPORATIVE",
         "ROLE_LEGAL_REPRESENTATIVE_VISOR",
         "ROLE_LEGAL_REPRESENTATIVE_EJECUTOR",
         "ROLE_FICO_VISOR",
         "ROLE_FICO_EJECUTOR",
         "ROLE_AUTHORIZER_VISOR",
         "ROLE_AUTHORIZER_EJECUTOR",
         "ROLE_OPERATOR_VISOR",
         "ROLE_OPERATOR_EJECUTOR"].each { roleName ->
           sql.execute("INSERT INTO ROLE(version,authority) VALUES (0,${roleName})")
         }
      }
    }
  }

}
