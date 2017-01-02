databaseChangeLog = {

    changeSet(author: "says (generated)", id: "1483062396097-1") {
        createTable(tableName: "purchase_order_payment_to_purchase") {
            column(name: "purchase_order_payments_id", type: "BIGINT")

            column(name: "payment_to_purchase_id", type: "BIGINT")
        }
    }

    changeSet(author: "says (generated)", id: "1483062396097-2") {
        addForeignKeyConstraint(baseColumnNames: "payment_to_purchase_id", baseTableName: "purchase_order_payment_to_purchase", constraintName: "FK_2hoido30po6klvme7xsojbcib", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "payment_to_purchase")
    }

    changeSet(author: "says (generated)", id: "1483062396097-3") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_payments_id", baseTableName: "purchase_order_payment_to_purchase", constraintName: "FK_g9bvx5cb2ynsq2oxeq094w31p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }
}
