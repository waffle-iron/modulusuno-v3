databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1481913809667-1") {
        addColumn(tableName: "purchase_order") {
            column(name: "original_date", type: "datetime")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1481913809667-2") {
        addColumn(tableName: "sale_order") {
            column(name: "original_date", type: "datetime")
        }
    }

}
