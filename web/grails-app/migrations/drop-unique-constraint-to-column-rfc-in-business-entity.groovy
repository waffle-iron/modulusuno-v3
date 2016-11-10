databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1464193670487-4") {
        dropUniqueConstraint(constraintName: "UC_BUSINESS_ENTITYRFC_COL", tableName: "business_entity")
    }
}
