databaseChangeLog = {

    changeSet(author: "makingdevs (generated)", id: "1478793307339-1") {
        createTable(tableName: "fees_receipt_authorization") {
            column(name: "fees_receipt_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-2") {
        createTable(tableName: "loan_order_s3asset") {
            column(name: "loan_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-3") {
        createTable(tableName: "loan_payment_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "loan_payment_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount_interest", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "amount_iva_interest", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "amount_to_capital", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_payment", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "days_period", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "loan_order_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-4") {
        createTable(tableName: "loan_payment_order_authorization") {
            column(name: "loan_payment_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-5") {
        createTable(tableName: "sale_order_s3asset") {
            column(name: "sale_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-6") {
        addColumn(tableName: "fees_receipt") {
            column(name: "bank_account_id", type: "bigint")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-7") {
        addColumn(tableName: "fees_receipt") {
            column(name: "collaborator_name", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-8") {
        addColumn(tableName: "cash_out_order") {
            column(name: "comments", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-9") {
        addColumn(tableName: "fees_receipt") {
            column(name: "company_id", type: "bigint") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-10") {
        addColumn(tableName: "loan_order") {
            column(name: "creditor_id", type: "bigint")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-11") {
        addColumn(tableName: "loan_order") {
            column(name: "loan_date", type: "datetime")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-12") {
        addColumn(tableName: "loan_order") {
            column(name: "rate", type: "decimal(19, 2)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-13") {
        addColumn(tableName: "loan_order") {
            column(name: "reject_comment", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-14") {
        addColumn(tableName: "cash_out_order") {
            column(name: "reject_reason", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-15") {
        addColumn(tableName: "loan_order") {
            column(name: "reject_reason", type: "varchar(255)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-16") {
        addColumn(tableName: "client_link") {
            column(name: "stp_clabe", type: "varchar(18)")
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-17") {
        addColumn(tableName: "loan_order") {
            column(name: "term", type: "integer") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-18") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "sale_order_s3asset", constraintName: "FK_14gx7m1jy60c780oe6uycmfug", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-19") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_3f85cq7on905fe0rwqwbvinnn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-20") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "fees_receipt", constraintName: "FK_64uboislmwsbb72nj98pwjt06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-21") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_documents_id", baseTableName: "loan_order_s3asset", constraintName: "FK_8vi2muat59yyggpgqvqpum19o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-22") {
        addForeignKeyConstraint(baseColumnNames: "loan_payment_order_authorizations_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_9jma5e7kyk19qyuircu3nnol0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_payment_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-23") {
        addForeignKeyConstraint(baseColumnNames: "creditor_id", baseTableName: "loan_order", constraintName: "FK_aoni3grb28trkd55k5qdvmmlp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-24") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "loan_order_s3asset", constraintName: "FK_cskx8lxe537k80wgvcaikhcl1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-25") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_authorizations_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_i98w7amj0ducvqps32ebja25r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-26") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_payment_order", constraintName: "FK_j4n82x30i201m3hur184i0sre", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-27") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_id", baseTableName: "loan_payment_order", constraintName: "FK_jfyrjp5jp8som68gi8i2xhpgf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-28") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "fees_receipt", constraintName: "FK_l8yr249frc4ndctg7d1ioc1fe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-29") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_o3g0cb3kil96la70xt16siocu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-30") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_documents_id", baseTableName: "sale_order_s3asset", constraintName: "FK_rco4kaeej2an07aasmqw2p538", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-31") {
        dropForeignKeyConstraint(baseTableName: "fees_receipt", constraintName: "FK_7q3lp7dsc51couicv3tdeuhoh")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-32") {
        dropForeignKeyConstraint(baseTableName: "processor_payroll_payroll", constraintName: "FK_d9u0dl6mex5wpx7o2ax1y2r4p")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-33") {
        dropForeignKeyConstraint(baseTableName: "processor_payroll", constraintName: "FK_dfw8x1hd8sf47lskaiphuusvl")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-34") {
        dropForeignKeyConstraint(baseTableName: "processor_payroll_payroll", constraintName: "FK_h4r345284os77tyws5bh4ty56")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-35") {
        dropForeignKeyConstraint(baseTableName: "payroll", constraintName: "FK_kfot01i7baasiwuerg4r5cxgp")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-36") {
        dropTable(tableName: "payroll")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-37") {
        dropTable(tableName: "processor_payroll")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-38") {
        dropTable(tableName: "processor_payroll_payroll")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-39") {
        dropColumn(columnName: "artemisa_id", tableName: "business_entity")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-40") {
        dropColumn(columnName: "artemisa_id", tableName: "company")
    }

    changeSet(author: "makingdevs (generated)", id: "1478793307339-41") {
        dropColumn(columnName: "business_entity_id", tableName: "fees_receipt")
    }
}
