databaseChangeLog = {
  changeSet(author:"tim (manual)", id: "growSizeNameSaleOrderItem") {
    modifyDataType (tableName: "sale_order_item", columnName: "name", newDataType: "VARCHAR(200)")
    addNotNullConstraint (columnDataType:"VARCHAR(200)", columnName:"name", tableName:"sale_order_item")
  }
}
