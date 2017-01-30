databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1485541989348-1") {
        addUniqueConstraint(columnNames: "corporate_url", constraintName: "UC_CORPORATECORPORATE_URL_COL", tableName: "corporate")
    }

    changeSet(author: "makingdevs (generated)", id: "1485541989348-2") {
        addUniqueConstraint(columnNames: "name_corporate", constraintName: "UC_CORPORATENAME_CORPORATE_COL", tableName: "corporate")
    }

}
