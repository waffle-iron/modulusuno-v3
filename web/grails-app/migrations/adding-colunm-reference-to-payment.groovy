databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1484844555584-1") {
        addColumn(tableName: "payment") {
            column(name: "rfc", type: "varchar(255)")
        }
    }

}
