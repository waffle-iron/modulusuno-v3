databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1478793827843-1") {
        addColumn(tableName: "business_entity") {
            column(name: "artemisa_id", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793827843-2") {
        addColumn(tableName: "company") {
            column(name: "artemisa_id", type: "varchar(255)")
        }
    }
}
