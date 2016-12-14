databaseChangeLog = {
  changeSet(author:"temoc (manual)", id: "changeTypeColumQuantityInPurchaseItem") {
    modifyDataType (tableName: "purchase_order_item", columnName: "quantity", newDataType: "decimal(5, 2)")
    addNotNullConstraint (columnDataType:"DECIMAL(5,2)", columnName:"quantity", tableName:"purchase_order_item")
  }
}
