databaseChangeLog = {
  changeSet(author:"temoc (manual)", id: "growSizeNameTo300InPurchaseOrderItem") {
    modifyDataType (tableName: "purchase_order_item", columnName: "name", newDataType: "VARCHAR(300)")
    addNotNullConstraint (columnDataType:"VARCHAR(300)", columnName:"name", tableName:"purchase_order_item")
  }
}
