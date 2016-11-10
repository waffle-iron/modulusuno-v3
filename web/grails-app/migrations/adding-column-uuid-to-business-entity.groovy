databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1476821893094-1") {
        addColumn(tableName: "business_entity") {
            column(name: "uuid", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

}
