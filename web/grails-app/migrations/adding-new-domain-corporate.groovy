databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1481578244506-1") {
        createTable(tableName: "corporate") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "corporatePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "corporate_url", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name_corporate", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-2") {
        createTable(tableName: "corporate_company") {
            column(name: "corporate_companies_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-3") {
        createTable(tableName: "corporate_user") {
            column(name: "corporate_users_id", type: "BIGINT")

            column(name: "user_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-4") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "corporate_user", constraintName: "FK_6aaj211p0i2g5rjxve1679j1c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-5") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "corporate_company", constraintName: "FK_97miav6mf441scxegv4yeahxn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-6") {
        addForeignKeyConstraint(baseColumnNames: "corporate_users_id", baseTableName: "corporate_user", constraintName: "FK_rim8kc6rbj535werdkg4wxqfm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corporate")
    }

    changeSet(author: "makingdevs (generated)", id: "1481578244506-7") {
        addForeignKeyConstraint(baseColumnNames: "corporate_companies_id", baseTableName: "corporate_company", constraintName: "FK_te8x5ha1oi9xmpbummb33gonr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corporate")
    }

}
