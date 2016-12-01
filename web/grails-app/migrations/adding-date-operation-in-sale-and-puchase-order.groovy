databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1480607868284-1") {
        addColumn(tableName: "sale_order") {
            column(name: "fecha_cobro", type: "datetime")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1480607868284-2") {
        addColumn(tableName: "purchase_order") {
            column(name: "fecha_pago", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }
}
