databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1485386249632-1") {
        addColumn(tableName: "cash_out_order") {
            column(name: "uuid", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

}
