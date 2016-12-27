databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1482816826760-1") {
        createTable(tableName: "stp_deposit") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "stp_depositPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_beneficiary", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "beneficiary_key", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "beneficiary_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "company_name_stp", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numerical_reference", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "operation_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "operation_number", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "payer_key", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "payer_name", type: "VARCHAR(255)")

            column(name: "payment_concept", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rfc_curp_beneficiary", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tracing_key", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type_account_beneficiary", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }
}
