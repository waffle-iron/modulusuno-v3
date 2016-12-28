databaseChangeLog = {

  changeSet(author: "egjimenezg (generated)", id: "1482941013206-1") {
    dropForeignKeyConstraint(baseTableName: "company_user", constraintName: "FK_c5ho50yl88ujw9fvhyicsmb13")
  }

  changeSet(author: "egjimenezg (generated)", id: "1482941013206-2") {
    dropForeignKeyConstraint(baseTableName: "company_user", constraintName: "FK_glhg26i028exp1dyq3wug04bh")
  }

  changeSet(author: "egjimenezg (generated)", id: "1482941013206-3") {
    dropForeignKeyConstraint(baseTableName: "company_user", constraintName: "FK_h26e77n4ja7q3bl2te4cvc1t0")
  }

  changeSet(author: "egjimenezg (generated)", id: "1482941013206-4") {
    dropTable(tableName: "company_user")
  }
}
