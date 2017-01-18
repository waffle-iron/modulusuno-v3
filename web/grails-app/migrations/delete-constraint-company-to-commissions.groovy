databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1484138833740-1") {
        dropNotNullConstraint(columnDataType: "bigint", columnName: "company_id", tableName: "commission")
    }
}
