databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1482161423280-1") {
        addColumn(tableName: "bank_account") {
            column(name: "concentradora", type: "bit") {
                constraints(nullable: "false")
            }
        }
    }

}
