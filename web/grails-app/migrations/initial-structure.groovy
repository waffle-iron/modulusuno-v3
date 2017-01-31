databaseChangeLog = {

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-1") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "addressPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "colony", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "country", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "federal_entity", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "street", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "street_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "suite", type: "VARCHAR(255)")

            column(name: "town", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "zip_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-2") {
        createTable(tableName: "authorization") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "authorizationPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-3") {
        createTable(tableName: "bank") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "bankPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "banking_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-4") {
        createTable(tableName: "bank_account") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "bank_accountPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_number", type: "VARCHAR(11)") {
                constraints(nullable: "false")
            }

            column(name: "banco_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "branch_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "clabe", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "concentradora", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-5") {
        createTable(tableName: "business_entity") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "business_entityPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "artemisa_id", type: "VARCHAR(255)")

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "website", type: "VARCHAR(50)")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-6") {
        createTable(tableName: "business_entity_address") {
            column(name: "business_entity_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-7") {
        createTable(tableName: "business_entity_bank_account") {
            column(name: "business_entity_banks_accounts_id", type: "BIGINT")

            column(name: "bank_account_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-8") {
        createTable(tableName: "cash_out_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "cash_out_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timone_account", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timone_uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-9") {
        createTable(tableName: "cash_out_order_authorization") {
            column(name: "cash_out_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-10") {
        createTable(tableName: "client_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "client_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "client_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "stp_clabe", type: "VARCHAR(18)")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-11") {
        createTable(tableName: "commission") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "commissionPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fee", type: "DECIMAL(11, 2)")

            column(name: "percentage", type: "DECIMAL(5, 2)")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-12") {
        createTable(tableName: "company") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "companyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "artemisa_id", type: "VARCHAR(255)")

            column(name: "bussiness_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "employee_numbers", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "gross_annual_billing", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "number_of_authorizations", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tax_regime", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "web_site", type: "VARCHAR(50)")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-13") {
        createTable(tableName: "company_address") {
            column(name: "company_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-14") {
        createTable(tableName: "company_bank_account") {
            column(name: "company_banks_accounts_id", type: "BIGINT")

            column(name: "bank_account_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-15") {
        createTable(tableName: "company_business_entity") {
            column(name: "company_business_entities_id", type: "BIGINT")

            column(name: "business_entity_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-16") {
        createTable(tableName: "company_rejected_log") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "company_rejected_logPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "asset_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reason", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "type_class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-17") {
        createTable(tableName: "company_s3asset") {
            column(name: "company_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-18") {
        createTable(tableName: "company_telephone") {
            column(name: "company_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-19") {
        createTable(tableName: "compose_name") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "compose_namePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "business_entity_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-20") {
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

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-21") {
        createTable(tableName: "corporate_company") {
            column(name: "corporate_companies_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-22") {
        createTable(tableName: "corporate_user") {
            column(name: "corporate_users_id", type: "BIGINT")

            column(name: "user_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-23") {
        createTable(tableName: "deposit_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "deposit_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid_transaction", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-24") {
        createTable(tableName: "deposit_order_authorization") {
            column(name: "deposit_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-25") {
        createTable(tableName: "deposit_order_s3asset") {
            column(name: "deposit_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-26") {
        createTable(tableName: "employee_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "employee_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "curp", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "employee_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-27") {
        createTable(tableName: "fees_receipt") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "fees_receiptPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "bank_account_id", type: "BIGINT")

            column(name: "collaborator_name", type: "VARCHAR(255)")

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "isr", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva_with_holding", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-28") {
        createTable(tableName: "fees_receipt_authorization") {
            column(name: "fees_receipt_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-29") {
        createTable(tableName: "fees_receipt_s3asset") {
            column(name: "fees_receipt_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-30") {
        createTable(tableName: "first_access_to_legal_representatives") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "first_access_to_legal_representativesPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "url_verification", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-31") {
        createTable(tableName: "legal_representatives_assets") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "legal_representatives_assetsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "asset", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-32") {
        createTable(tableName: "loan_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "loan_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "creditor_id", type: "BIGINT")

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "loan_date", type: "datetime")

            column(name: "rate", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "term", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-33") {
        createTable(tableName: "loan_order_authorization") {
            column(name: "loan_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-34") {
        createTable(tableName: "loan_order_s3asset") {
            column(name: "loan_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-35") {
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

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-36") {
        createTable(tableName: "loan_payment_order_authorization") {
            column(name: "loan_payment_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-37") {
        createTable(tableName: "modulus_uno_account") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "modulus_uno_accountPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "balance", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "integra_uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "stp_clabe", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timone_uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-38") {
        createTable(tableName: "movimientos_bancarios") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "movimientos_bancariosPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "concept", type: "VARCHAR(200)") {
                constraints(nullable: "false")
            }

            column(name: "cuenta_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "date_event", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "reference", type: "VARCHAR(200)")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-39") {
        createTable(tableName: "payment") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "paymentPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "sale_order_id", type: "BIGINT")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-40") {
        createTable(tableName: "payment_to_purchase") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "payment_to_purchasePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-41") {
        createTable(tableName: "product") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "productPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "currency_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "ieps", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "sku", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "unit_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-42") {
        createTable(tableName: "profile") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "profilePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "birth_date", type: "datetime")

            column(name: "curp", type: "VARCHAR(18)")

            column(name: "email", type: "VARCHAR(200)") {
                constraints(nullable: "false")
            }

            column(name: "gender", type: "VARCHAR(255)")

            column(name: "last_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "mother_last_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "nationality", type: "VARCHAR(255)")

            column(name: "rfc", type: "VARCHAR(50)")

            column(name: "trademark", type: "VARCHAR(100)")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-43") {
        createTable(tableName: "profile_s3asset") {
            column(name: "profile_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-44") {
        createTable(tableName: "profile_telephone") {
            column(name: "profile_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-45") {
        createTable(tableName: "provider_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "provider_linkPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "provider_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-46") {
        createTable(tableName: "purchase_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "purchase_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bank_account_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "external_id", type: "VARCHAR(255)")

            column(name: "fecha_pago", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "is_anticipated", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "is_money_back_order", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(300)")

            column(name: "original_date", type: "datetime")

            column(name: "provider_name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-47") {
        createTable(tableName: "purchase_order_authorization") {
            column(name: "purchase_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-48") {
        createTable(tableName: "purchase_order_item") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "purchase_order_itemPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "currency_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "ieps", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "purchase_order_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "unit_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-49") {
        createTable(tableName: "purchase_order_payment_to_purchase") {
            column(name: "purchase_order_payments_id", type: "BIGINT")

            column(name: "payment_to_purchase_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-50") {
        createTable(tableName: "purchase_order_s3asset") {
            column(name: "purchase_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-51") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "registration_codePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(200)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-52") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-53") {
        createTable(tableName: "s3asset") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "s3assetPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bucket", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "local_path", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "local_url", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "mime_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "original_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-54") {
        createTable(tableName: "sale_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sale_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "client_name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "external_id", type: "VARCHAR(255)")

            column(name: "fecha_cobro", type: "datetime")

            column(name: "folio", type: "VARCHAR(255)")

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "note", type: "VARCHAR(300)")

            column(name: "original_date", type: "datetime")

            column(name: "pdf_template", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-55") {
        createTable(tableName: "sale_order_address") {
            column(name: "sale_order_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-56") {
        createTable(tableName: "sale_order_authorization") {
            column(name: "sale_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-57") {
        createTable(tableName: "sale_order_item") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "sale_order_itemPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "currency_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "ieps", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "iva", type: "DECIMAL(5, 2)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "sale_order_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sku", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "unit_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-58") {
        createTable(tableName: "sale_order_s3asset") {
            column(name: "sale_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-59") {
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

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-60") {
        createTable(tableName: "telephone") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "telephonePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "extension", type: "VARCHAR(10)")

            column(name: "number", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-61") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "profile_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-62") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-63") {
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

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-64") {
        createTable(tableName: "user_role_company_role") {
            column(name: "user_role_company_roles_id", type: "BIGINT")

            column(name: "role_id", type: "BIGINT")
        }
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-65") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-66") {
        addUniqueConstraint(columnNames: "company_id, sku", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "product")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-67") {
        addUniqueConstraint(columnNames: "rfc", constraintName: "UC_COMPANYRFC_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-68") {
        addUniqueConstraint(columnNames: "email", constraintName: "UC_PROFILEEMAIL_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "profile")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-69") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "role")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-70") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-71") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "sale_order_s3asset", constraintName: "FK_14gx7m1jy60c780oe6uycmfug", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-72") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_18m22ybal0ga91hy3s5l66bul", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-73") {
        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "user", constraintName: "FK_1mcjtpxmwom9h9bf2q0k412e0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-74") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "payment", constraintName: "FK_26aw28fqp8nv9kd2n9kug1ate", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-75") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "sale_order_authorization", constraintName: "FK_29tfk85uscogyuyhnmogjstyq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-76") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_id", baseTableName: "payment", constraintName: "FK_2b7qu5poo9sv9mtyuw9xhkcl2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-77") {
        addForeignKeyConstraint(baseColumnNames: "payment_to_purchase_id", baseTableName: "purchase_order_payment_to_purchase", constraintName: "FK_2hoido30po6klvme7xsojbcib", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "payment_to_purchase")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-78") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_3f85cq7on905fe0rwqwbvinnn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-79") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "employee_link", constraintName: "FK_3y480lpgswma3dlahkas0piv2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-80") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "product", constraintName: "FK_45g3a4rexapplpirj3jor716p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-81") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "purchase_order_authorization", constraintName: "FK_5a9nmc0j5rhmo268sm9f08lef", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-82") {
        addForeignKeyConstraint(baseColumnNames: "cash_out_order_authorizations_id", baseTableName: "cash_out_order_authorization", constraintName: "FK_5b91qa29nyutp6sh2fo4x4tn6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cash_out_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-83") {
        addForeignKeyConstraint(baseColumnNames: "company_business_entities_id", baseTableName: "company_business_entity", constraintName: "FK_5iomp5vtj1eh4ykkyf02cd808", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-84") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_id", baseTableName: "sale_order_item", constraintName: "FK_5jo2bsgk40m6yjjd587bdvp2k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-85") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "fees_receipt", constraintName: "FK_64uboislmwsbb72nj98pwjt06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-86") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "corporate_user", constraintName: "FK_6aaj211p0i2g5rjxve1679j1c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-87") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_documents_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_78mxqus4c33f8agef1720orat", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-88") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "cash_out_order_authorization", constraintName: "FK_7js7ru525ir9t0f0atxgr143u", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-89") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role_company", constraintName: "FK_7khskpewos3jrxg9umn6n6mj5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-90") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_order_authorization", constraintName: "FK_7s9iidg2nghqx5vm8ufivle2f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-91") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_documents_id", baseTableName: "loan_order_s3asset", constraintName: "FK_8vi2muat59yyggpgqvqpum19o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-92") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "corporate_company", constraintName: "FK_97miav6mf441scxegv4yeahxn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-93") {
        addForeignKeyConstraint(baseColumnNames: "loan_payment_order_authorizations_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_9jma5e7kyk19qyuircu3nnol0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_payment_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-94") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "commission", constraintName: "FK_a64q6jugjh0qqbrmik3wc4m57", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-95") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_addresses_id", baseTableName: "sale_order_address", constraintName: "FK_ajahww2oyodgs8nkgxii8vcyc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-96") {
        addForeignKeyConstraint(baseColumnNames: "creditor_id", baseTableName: "loan_order", constraintName: "FK_aoni3grb28trkd55k5qdvmmlp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-97") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-98") {
        addForeignKeyConstraint(baseColumnNames: "profile_telephones_id", baseTableName: "profile_telephone", constraintName: "FK_atewti54qfefm3c47tl56bc6q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-99") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "company_telephone", constraintName: "FK_b9vi6vh4wjnnlhg3fiqhibbn0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-100") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_addresses_id", baseTableName: "business_entity_address", constraintName: "FK_bi96koa3fssjo9uxnnivj99hy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-101") {
        addForeignKeyConstraint(baseColumnNames: "company_banks_accounts_id", baseTableName: "company_bank_account", constraintName: "FK_br0e1cf387hqfyl8r6kwgda39", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-102") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "company_business_entity", constraintName: "FK_cndhy0ioljqk55e72fq57caui", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-103") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "loan_order_s3asset", constraintName: "FK_cskx8lxe537k80wgvcaikhcl1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-104") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "modulus_uno_account", constraintName: "FK_d1mewcfc7h3b94jtl0502o4og", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-105") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "sale_order_address", constraintName: "FK_eoqg04g2l1a8q8igjc4rletj8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-106") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_fc4511gyg27b7fsbnh548xidq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-107") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "sale_order", constraintName: "FK_ffok8uw34n09jms16o7syeh3v", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-108") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "company_address", constraintName: "FK_fuicds1wwge4vwujato9u3cks", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-109") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_payments_id", baseTableName: "purchase_order_payment_to_purchase", constraintName: "FK_g9bvx5cb2ynsq2oxeq094w31p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-110") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_authorizations_id", baseTableName: "deposit_order_authorization", constraintName: "FK_gd2dhyoj1fmwk484482lnquso", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-111") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_authorizations_id", baseTableName: "loan_order_authorization", constraintName: "FK_gh6vd4lpw7rfi6udgpmefs9r7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-112") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role_company_role", constraintName: "FK_hluxnc67592jgfpl98v7pnscx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-113") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_documents_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_i78rkp7yywh4tembtx5ihxm7n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-114") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_authorizations_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_i98w7amj0ducvqps32ebja25r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-115") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-116") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_payment_order", constraintName: "FK_j4n82x30i201m3hur184i0sre", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-117") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_id", baseTableName: "loan_payment_order", constraintName: "FK_jfyrjp5jp8som68gi8i2xhpgf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-118") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "business_entity_address", constraintName: "FK_jur33w6ryvek3oa3dngwt8lqw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-119") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "purchase_order", constraintName: "FK_l0jp8830qdyelrll7bkd2w846", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-120") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "fees_receipt", constraintName: "FK_l8yr249frc4ndctg7d1ioc1fe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-121") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "user_role_company", constraintName: "FK_lbbwhrvw4lp38agdl1nr1bi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-122") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "deposit_order_authorization", constraintName: "FK_lfljxhh8n5pik85wl02g2cuj4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-123") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_mxvud6egd9nvaa0dtuo4rch8s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-124") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_banks_accounts_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ndjediucqay0cjd4o508k0yxf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-125") {
        addForeignKeyConstraint(baseColumnNames: "company_addresses_id", baseTableName: "company_address", constraintName: "FK_nrso9xpub8w0ncciyafd6qq4d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-126") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ny4e4sb5gwjknkbkimh9ad12e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-127") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_o3g0cb3kil96la70xt16siocu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-128") {
        addForeignKeyConstraint(baseColumnNames: "user_role_company_roles_id", baseTableName: "user_role_company_role", constraintName: "FK_ojo4se5xxv78pa3dceihpe51l", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user_role_company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-129") {
        addForeignKeyConstraint(baseColumnNames: "company_documents_id", baseTableName: "company_s3asset", constraintName: "FK_owheu11vhnpfmqfpd4y01k1ct", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-130") {
        addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "bank_account", constraintName: "FK_p03yka0x638p1lt4fu2f8cafh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-131") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "authorization", constraintName: "FK_p0svctns3tvk3nsg2ofu95f6y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-132") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "cash_out_order", constraintName: "FK_p2n2ovcwouuhbu6e5j3nsmdwi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-133") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "company_bank_account", constraintName: "FK_plp60f9wa9rbix1e3wegi3o4t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-134") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "provider_link", constraintName: "FK_q6rmqkgm8gwg1qtpvu4eu25m5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-135") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_qatwcpuy6ur5hskwnmo8spjdw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-136") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "company_s3asset", constraintName: "FK_qmcq07p0niw1bxin654va8xxv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-137") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "deposit_order", constraintName: "FK_qxtq9xmyhwqmc3b2o4ty4ge8g", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-138") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_authorizations_id", baseTableName: "sale_order_authorization", constraintName: "FK_qxug1aj0k4b5tbixbm2a8g9nj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-139") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_id", baseTableName: "purchase_order_item", constraintName: "FK_qyodi27brvg62u48ffvniw5g4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-140") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "purchase_order", constraintName: "FK_r9qv2bxe3le52lt36ubnjramx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-141") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_documents_id", baseTableName: "sale_order_s3asset", constraintName: "FK_rco4kaeej2an07aasmqw2p538", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-142") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_authorizations_id", baseTableName: "purchase_order_authorization", constraintName: "FK_ri9istm8vp3x6cop99vhxlhc5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-143") {
        addForeignKeyConstraint(baseColumnNames: "corporate_users_id", baseTableName: "corporate_user", constraintName: "FK_rim8kc6rbj535werdkg4wxqfm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corporate")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-144") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "purchase_order_s3asset", constraintName: "FK_rjfla2m6jjrq4relh4idhb9c7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-145") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "compose_name", constraintName: "FK_rjqgae44682dns471c7hweb8e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-146") {
        addForeignKeyConstraint(baseColumnNames: "company_telephones_id", baseTableName: "company_telephone", constraintName: "FK_s14mlklg07gxek8vc2qmg5yk4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-147") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "client_link", constraintName: "FK_s2tgg8i7gbg9fgnkb7j05vhh1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-148") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "profile_telephone", constraintName: "FK_s6qu1iyo2xra9o42sj0vney", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-149") {
        addForeignKeyConstraint(baseColumnNames: "cuenta_id", baseTableName: "movimientos_bancarios", constraintName: "FK_sorpav5eibs6myntu9bm6oerw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-150") {
        addForeignKeyConstraint(baseColumnNames: "corporate_companies_id", baseTableName: "corporate_company", constraintName: "FK_te8x5ha1oi9xmpbummb33gonr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "corporate")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-151") {
        addForeignKeyConstraint(baseColumnNames: "profile_documents_id", baseTableName: "profile_s3asset", constraintName: "FK_tf5ckj7ugmmbtd0nku9nm1hs5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-152") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_documents_id", baseTableName: "purchase_order_s3asset", constraintName: "FK_thyldems4h03uogsgduropc0i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-153") {
        addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "cash_out_order", constraintName: "FK_tn08matxb1rdwy9879bbx2bsk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-154") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "profile_s3asset", constraintName: "FK_vevapj0ikfqe1s21iy1d9yyf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "cggg88jorge (generated)", id: "1484852833580-155") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_order", constraintName: "FK_wbaipjtxlax1ajhy32amn5pe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company")
    }
}
