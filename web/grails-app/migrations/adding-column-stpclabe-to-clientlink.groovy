databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1476805132398-1") {
        addColumn(tableName: "client_link") {
            column(name: "stp_clabe", type: "varchar(18)")
        }
    }
}
