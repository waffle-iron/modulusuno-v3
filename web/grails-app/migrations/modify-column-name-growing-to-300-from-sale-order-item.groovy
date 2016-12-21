databaseChangeLog = {
  changeSet(author:"temoc (manual)", id: "growSizeNameTo300InSaleOrderItem") {
    modifyDataType (tableName: "sale_order_item", columnName: "name", newDataType: "VARCHAR(300)")
    addNotNullConstraint (columnDataType:"VARCHAR(300)", columnName:"name", tableName:"sale_order_item")
  }
}
