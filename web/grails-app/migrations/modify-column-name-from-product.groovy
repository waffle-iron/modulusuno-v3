databaseChangeLog = {
  changeSet(author:"tim (manual)", id: "growSizeNameProduct") {
    modifyDataType (tableName: "product", columnName: "name", newDataType: "VARCHAR(200)")
    addNotNullConstraint (columnDataType:"VARCHAR(200)", columnName:"name", tableName:"product")
  }
}
