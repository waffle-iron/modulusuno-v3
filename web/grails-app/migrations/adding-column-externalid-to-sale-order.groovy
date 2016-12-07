databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1481123206317-1") {
        addColumn(tableName: "sale_order") {
            column(name: "external_id", type: "varchar(255)")
        }
    }
}
