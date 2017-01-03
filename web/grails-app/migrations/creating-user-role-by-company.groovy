databaseChangeLog = {

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-1") {
        createTable(tableName: "user_role_company") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "user_role_companyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-2") {
        createTable(tableName: "user_role_company_role") {
            column(name: "user_role_company_roles_id", type: "BIGINT")

            column(name: "role_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-3") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role_company", constraintName: "FK_7khskpewos3jrxg9umn6n6mj5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-4") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role_company_role", constraintName: "FK_hluxnc67592jgfpl98v7pnscx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-5") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "user_role_company", constraintName: "FK_lbbwhrvw4lp38agdl1nr1bi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1482764647362-6") {
        addForeignKeyConstraint(baseColumnNames: "user_role_company_roles_id", baseTableName: "user_role_company_role", constraintName: "FK_ojo4se5xxv78pa3dceihpe51l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user_role_company")
    }
}
