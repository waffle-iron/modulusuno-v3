databaseChangeLog = {
  changeSet(author:"temoc (manual)", id: "addingNotNullConstraintToQuantityInSaleOrderItem") {
    addNotNullConstraint (columnDataType:"DECIMAL(5,2)", columnName:"quantity", tableName:"sale_order_item")
  }
}
