databaseChangeLog = {
  changeSet(author:"temoc (manual)", id: "growSizeNameTo300FromProduct") {
    modifyDataType (tableName: "product", columnName: "name", newDataType: "VARCHAR(300)")
    addNotNullConstraint (columnDataType:"VARCHAR(300)", columnName:"name", tableName:"product")
  }
}
