databaseChangeLog = {

    changeSet(author: "says (generated)", id: "1482542557943-2") {
        addColumn(tableName: "purchase_order") {
            column(name: "note", type: "varchar(300)")
        }
    }

    changeSet(author: "says (generated)", id: "1482542557943-3") {
        addColumn(tableName: "sale_order") {
            column(name: "note", type: "varchar(300)")
        }
    }
}
