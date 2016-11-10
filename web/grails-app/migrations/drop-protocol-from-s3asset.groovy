databaseChangeLog = {

    changeSet(author: "josdem (generated)", id: "1468956365125-1") {
        createTable(tableName: "sale_order_s3asset") {
            column(name: "sale_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1468956365125-2") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "sale_order_s3asset", constraintName: "FK_14gx7m1jy60c780oe6uycmfug", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1468956365125-3") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_documents_id", baseTableName: "sale_order_s3asset", constraintName: "FK_rco4kaeej2an07aasmqw2p538", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "josdem (generated)", id: "1468956365125-4") {
        dropColumn(columnName: "protocol", tableName: "s3asset")
    }
}
