databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1464127678535-1") {
        addUniqueConstraint(columnNames: "rfc", constraintName: "UC_BUSINESS_ENTITYRFC_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "business_entity")
    }

}
