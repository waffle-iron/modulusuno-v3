databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1483071838110-1") {
        addColumn(tableName: "sale_order") {
            column(name: "pdf_template", type: "varchar(255)")
        }
    }
}
