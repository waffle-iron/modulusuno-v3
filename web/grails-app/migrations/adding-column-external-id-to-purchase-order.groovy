databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1481124458600-1") {
        addColumn(tableName: "purchase_order") {
            column(name: "external_id", type: "varchar(255)")
        }
    }
}
