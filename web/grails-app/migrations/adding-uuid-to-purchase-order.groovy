databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1485186803423-1") {
        addColumn(tableName: "purchase_order") {
            column(name: "uuid", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }
}
