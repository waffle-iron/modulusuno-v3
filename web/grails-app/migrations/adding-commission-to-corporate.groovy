databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1484141759512-1") {
        createTable(tableName: "corporate_commission") {
            column(name: "corporate_commissions_id", type: "BIGINT")

            column(name: "commission_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1484141759512-2") {
        addForeignKeyConstraint(baseColumnNames: "corporate_commissions_id", baseTableName: "corporate_commission", constraintName: "FK_aljb8xf9gee052wj1h3kcm7c6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corporate")
    }

    changeSet(author: "makingdevs (generated)", id: "1484141759512-3") {
        addForeignKeyConstraint(baseColumnNames: "commission_id", baseTableName: "corporate_commission", constraintName: "FK_o2isuoy7b0sec6ylb28n20i8v", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "commission")
    }
}
