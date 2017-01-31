databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1485444060777-1") {
        addColumn(tableName: "fees_receipt") {
            column(name: "uuid", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

}
