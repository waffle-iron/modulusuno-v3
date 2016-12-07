databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1481124018633-1") {
        dropNotNullConstraint(columnDataType: "varchar(200)", columnName: "reference", tableName: "movimientos_bancarios")
    }
}
