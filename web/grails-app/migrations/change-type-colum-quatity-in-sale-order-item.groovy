databaseChangeLog = {
  changeSet(author:"says33 (manual)", id: "changeTypeColumQuantity") {
    modifyDataType (tableName: "sale_order_item", columnName: "quantity", newDataType: "decimal(5, 2)")
  }
}
