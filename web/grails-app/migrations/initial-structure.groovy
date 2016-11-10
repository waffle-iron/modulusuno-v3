databaseChangeLog = {

    changeSet(author: "josdem (generated)", id: "1463151310620-1") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-2") {
        createTable(tableName: "authorization") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-3") {
        createTable(tableName: "bank") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-4") {
        createTable(tableName: "bank_account") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-5") {
        createTable(tableName: "business_entity") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "website", type: "VARCHAR(50)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-6") {
        createTable(tableName: "business_entity_address") {
            column(name: "business_entity_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-7") {
        createTable(tableName: "business_entity_bank_account") {
            column(name: "bank_account_id", type: "BIGINT")

            column(name: "business_entity_banks_accounts_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-8") {
        createTable(tableName: "cash_out_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timone_uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "timone_account", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-9") {
        createTable(tableName: "cash_out_order_authorization") {
            column(name: "cash_out_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-10") {
        createTable(tableName: "client_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-11") {
        createTable(tableName: "commission") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fee", type: "DECIMAL(11, 2)")

            column(name: "percentage", type: "DECIMAL(11, 2)")

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-12") {
        createTable(tableName: "company") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "bussiness_name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "employee_numbers", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "gross_annual_billing", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "web_site", type: "VARCHAR(50)")

            column(name: "number_of_authorizations", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "tax_regime", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-13") {
        createTable(tableName: "company_address") {
            column(name: "company_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-14") {
        createTable(tableName: "company_bank_account") {
            column(name: "company_banks_accounts_id", type: "BIGINT")

            column(name: "bank_account_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-15") {
        createTable(tableName: "company_business_entity") {
            column(name: "company_business_entities_id", type: "BIGINT")

            column(name: "business_entity_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-16") {
        createTable(tableName: "company_rejected_log") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "status", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "type_class", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-17") {
        createTable(tableName: "company_s3asset") {
            column(name: "company_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-18") {
        createTable(tableName: "company_telephone") {
            column(name: "company_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-19") {
        createTable(tableName: "company_user") {
            column(name: "company_actors_id", type: "BIGINT")

            column(name: "user_id", type: "BIGINT")

            column(name: "company_legal_representatives_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-20") {
        createTable(tableName: "compose_name") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-21") {
        createTable(tableName: "deposit_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid_transaction", type: "VARCHAR(255)")

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-22") {
        createTable(tableName: "deposit_order_authorization") {
            column(name: "deposit_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-23") {
        createTable(tableName: "deposit_order_s3asset") {
            column(name: "deposit_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-24") {
        createTable(tableName: "employee_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "employee_ref", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "curp", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-25") {
        createTable(tableName: "fees_receipt") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(11, 2)") {
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

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "bank_account_id", type: "BIGINT")

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "collaborator_name", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-26") {
        createTable(tableName: "fees_receipt_authorization") {
            column(name: "fees_receipt_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-27") {
        createTable(tableName: "fees_receipt_s3asset") {
            column(name: "fees_receipt_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-28") {
        createTable(tableName: "first_access_to_legal_representatives") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "company_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BIT(1)") {
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

    changeSet(author: "josdem (generated)", id: "1463151310620-29") {
        createTable(tableName: "legal_representatives_assets") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-30") {
        createTable(tableName: "loan_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "creditor_id", type: "BIGINT")

            column(name: "rate", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "term", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "loan_date", type: "datetime")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-31") {
        createTable(tableName: "loan_order_authorization") {
            column(name: "loan_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-32") {
        createTable(tableName: "loan_order_s3asset") {
            column(name: "loan_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-33") {
        createTable(tableName: "loan_payment_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-34") {
        createTable(tableName: "loan_payment_order_authorization") {
            column(name: "loan_payment_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-35") {
        createTable(tableName: "modulus_uno_account") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-36") {
        createTable(tableName: "payment") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-37") {
        createTable(tableName: "product") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "name", type: "VARCHAR(100)") {
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

    changeSet(author: "josdem (generated)", id: "1463151310620-38") {
        createTable(tableName: "profile") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-39") {
        createTable(tableName: "profile_s3asset") {
            column(name: "profile_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-40") {
        createTable(tableName: "profile_telephone") {
            column(name: "profile_telephones_id", type: "BIGINT")

            column(name: "telephone_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-41") {
        createTable(tableName: "provider_link") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-42") {
        createTable(tableName: "purchase_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
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

            column(name: "provider_name", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "is_anticipated", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "bank_account_id", type: "BIGINT")

            column(name: "is_money_back_order", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "reject_comment", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-43") {
        createTable(tableName: "purchase_order_authorization") {
            column(name: "purchase_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-44") {
        createTable(tableName: "purchase_order_item") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "purchase_order_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "unit_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-45") {
        createTable(tableName: "purchase_order_s3asset") {
            column(name: "purchase_order_documents_id", type: "BIGINT")

            column(name: "s3asset_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-46") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-47") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-48") {
        createTable(tableName: "s3asset") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "protocol", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "BIT(1)") {
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

    changeSet(author: "josdem (generated)", id: "1463151310620-49") {
        createTable(tableName: "sale_order") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "client_name", type: "VARCHAR(300)") {
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

            column(name: "rfc", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)")

            column(name: "comments", type: "VARCHAR(255)")

            column(name: "reject_reason", type: "VARCHAR(255)")

            column(name: "folio", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-50") {
        createTable(tableName: "sale_order_address") {
            column(name: "sale_order_addresses_id", type: "BIGINT")

            column(name: "address_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-51") {
        createTable(tableName: "sale_order_authorization") {
            column(name: "sale_order_authorizations_id", type: "BIGINT")

            column(name: "authorization_id", type: "BIGINT")
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-52") {
        createTable(tableName: "sale_order_item") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

            column(name: "name", type: "VARCHAR(100)") {
                constraints(nullable: "false")
            }

            column(name: "price", type: "DECIMAL(11, 2)") {
                constraints(nullable: "false")
            }

            column(name: "quantity", type: "INT") {
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

    changeSet(author: "josdem (generated)", id: "1463151310620-53") {
        createTable(tableName: "telephone") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
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

    changeSet(author: "josdem (generated)", id: "1463151310620-54") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BIT(1)") {
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

    changeSet(author: "josdem (generated)", id: "1463151310620-55") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-56") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "PRIMARY", tableName: "user_role")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-57") {
        addUniqueConstraint(columnNames: "rfc", constraintName: "UC_COMPANYRFC_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-58") {
        addUniqueConstraint(columnNames: "email", constraintName: "UC_PROFILEEMAIL_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "profile")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-59") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "role")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-60") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "user")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-61") {
        addUniqueConstraint(columnNames: "company_id, sku", constraintName: "company_id", deferrable: "false", disabled: "false", initiallyDeferred: "false", tableName: "product")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-62") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_18m22ybal0ga91hy3s5l66bul", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-63") {
        addForeignKeyConstraint(baseColumnNames: "profile_id", baseTableName: "user", constraintName: "FK_1mcjtpxmwom9h9bf2q0k412e0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-64") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "payment", constraintName: "FK_26aw28fqp8nv9kd2n9kug1ate", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-65") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "sale_order_authorization", constraintName: "FK_29tfk85uscogyuyhnmogjstyq", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-66") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_id", baseTableName: "payment", constraintName: "FK_2b7qu5poo9sv9mtyuw9xhkcl2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-67") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_3f85cq7on905fe0rwqwbvinnn", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-68") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "employee_link", constraintName: "FK_3y480lpgswma3dlahkas0piv2", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-69") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "product", constraintName: "FK_45g3a4rexapplpirj3jor716p", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-70") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "purchase_order_authorization", constraintName: "FK_5a9nmc0j5rhmo268sm9f08lef", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-71") {
        addForeignKeyConstraint(baseColumnNames: "cash_out_order_authorizations_id", baseTableName: "cash_out_order_authorization", constraintName: "FK_5b91qa29nyutp6sh2fo4x4tn6", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "cash_out_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-72") {
        addForeignKeyConstraint(baseColumnNames: "company_business_entities_id", baseTableName: "company_business_entity", constraintName: "FK_5iomp5vtj1eh4ykkyf02cd808", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-73") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_id", baseTableName: "sale_order_item", constraintName: "FK_5jo2bsgk40m6yjjd587bdvp2k", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-74") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "fees_receipt", constraintName: "FK_64uboislmwsbb72nj98pwjt06", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-75") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_documents_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_78mxqus4c33f8agef1720orat", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-76") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "cash_out_order_authorization", constraintName: "FK_7js7ru525ir9t0f0atxgr143u", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-77") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_order_authorization", constraintName: "FK_7s9iidg2nghqx5vm8ufivle2f", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-78") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_documents_id", baseTableName: "loan_order_s3asset", constraintName: "FK_8vi2muat59yyggpgqvqpum19o", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-79") {
        addForeignKeyConstraint(baseColumnNames: "loan_payment_order_authorizations_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_9jma5e7kyk19qyuircu3nnol0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loan_payment_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-80") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "commission", constraintName: "FK_a64q6jugjh0qqbrmik3wc4m57", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-81") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_addresses_id", baseTableName: "sale_order_address", constraintName: "FK_ajahww2oyodgs8nkgxii8vcyc", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-82") {
        addForeignKeyConstraint(baseColumnNames: "creditor_id", baseTableName: "loan_order", constraintName: "FK_aoni3grb28trkd55k5qdvmmlp", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-83") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK_apcc8lxk2xnug8377fatvbn04", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-84") {
        addForeignKeyConstraint(baseColumnNames: "profile_telephones_id", baseTableName: "profile_telephone", constraintName: "FK_atewti54qfefm3c47tl56bc6q", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-85") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "company_telephone", constraintName: "FK_b9vi6vh4wjnnlhg3fiqhibbn0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-86") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_addresses_id", baseTableName: "business_entity_address", constraintName: "FK_bi96koa3fssjo9uxnnivj99hy", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-87") {
        addForeignKeyConstraint(baseColumnNames: "company_banks_accounts_id", baseTableName: "company_bank_account", constraintName: "FK_br0e1cf387hqfyl8r6kwgda39", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-88") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "company_user", constraintName: "FK_c5ho50yl88ujw9fvhyicsmb13", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-89") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "company_business_entity", constraintName: "FK_cndhy0ioljqk55e72fq57caui", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-90") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "loan_order_s3asset", constraintName: "FK_cskx8lxe537k80wgvcaikhcl1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-91") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "modulus_uno_account", constraintName: "FK_d1mewcfc7h3b94jtl0502o4og", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-92") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "sale_order_address", constraintName: "FK_eoqg04g2l1a8q8igjc4rletj8", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-93") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "deposit_order_s3asset", constraintName: "FK_fc4511gyg27b7fsbnh548xidq", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-94") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "sale_order", constraintName: "FK_ffok8uw34n09jms16o7syeh3v", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-95") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "company_address", constraintName: "FK_fuicds1wwge4vwujato9u3cks", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-96") {
        addForeignKeyConstraint(baseColumnNames: "deposit_order_authorizations_id", baseTableName: "deposit_order_authorization", constraintName: "FK_gd2dhyoj1fmwk484482lnquso", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "deposit_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-97") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_authorizations_id", baseTableName: "loan_order_authorization", constraintName: "FK_gh6vd4lpw7rfi6udgpmefs9r7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-98") {
        addForeignKeyConstraint(baseColumnNames: "company_actors_id", baseTableName: "company_user", constraintName: "FK_glhg26i028exp1dyq3wug04bh", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-99") {
        addForeignKeyConstraint(baseColumnNames: "company_legal_representatives_id", baseTableName: "company_user", constraintName: "FK_h26e77n4ja7q3bl2te4cvc1t0", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-100") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_documents_id", baseTableName: "fees_receipt_s3asset", constraintName: "FK_i78rkp7yywh4tembtx5ihxm7n", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-101") {
        addForeignKeyConstraint(baseColumnNames: "fees_receipt_authorizations_id", baseTableName: "fees_receipt_authorization", constraintName: "FK_i98w7amj0ducvqps32ebja25r", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "fees_receipt")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-102") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-103") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_payment_order", constraintName: "FK_j4n82x30i201m3hur184i0sre", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-104") {
        addForeignKeyConstraint(baseColumnNames: "loan_order_id", baseTableName: "loan_payment_order", constraintName: "FK_jfyrjp5jp8som68gi8i2xhpgf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "loan_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-105") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "business_entity_address", constraintName: "FK_jur33w6ryvek3oa3dngwt8lqw", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-106") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "purchase_order", constraintName: "FK_l0jp8830qdyelrll7bkd2w846", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-107") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "fees_receipt", constraintName: "FK_l8yr249frc4ndctg7d1ioc1fe", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-108") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "deposit_order_authorization", constraintName: "FK_lfljxhh8n5pik85wl02g2cuj4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-109") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_mxvud6egd9nvaa0dtuo4rch8s", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-110") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_banks_accounts_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ndjediucqay0cjd4o508k0yxf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-111") {
        addForeignKeyConstraint(baseColumnNames: "company_addresses_id", baseTableName: "company_address", constraintName: "FK_nrso9xpub8w0ncciyafd6qq4d", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-112") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "business_entity_bank_account", constraintName: "FK_ny4e4sb5gwjknkbkimh9ad12e", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-113") {
        addForeignKeyConstraint(baseColumnNames: "authorization_id", baseTableName: "loan_payment_order_authorization", constraintName: "FK_o3g0cb3kil96la70xt16siocu", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "authorization")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-114") {
        addForeignKeyConstraint(baseColumnNames: "company_documents_id", baseTableName: "company_s3asset", constraintName: "FK_owheu11vhnpfmqfpd4y01k1ct", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-115") {
        addForeignKeyConstraint(baseColumnNames: "banco_id", baseTableName: "bank_account", constraintName: "FK_p03yka0x638p1lt4fu2f8cafh", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-116") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "authorization", constraintName: "FK_p0svctns3tvk3nsg2ofu95f6y", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-117") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "cash_out_order", constraintName: "FK_p2n2ovcwouuhbu6e5j3nsmdwi", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-118") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "company_bank_account", constraintName: "FK_plp60f9wa9rbix1e3wegi3o4t", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-119") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "provider_link", constraintName: "FK_q6rmqkgm8gwg1qtpvu4eu25m5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-120") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "first_access_to_legal_representatives", constraintName: "FK_qatwcpuy6ur5hskwnmo8spjdw", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-121") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "company_s3asset", constraintName: "FK_qmcq07p0niw1bxin654va8xxv", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-122") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "deposit_order", constraintName: "FK_qxtq9xmyhwqmc3b2o4ty4ge8g", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-123") {
        addForeignKeyConstraint(baseColumnNames: "sale_order_authorizations_id", baseTableName: "sale_order_authorization", constraintName: "FK_qxug1aj0k4b5tbixbm2a8g9nj", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "sale_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-124") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_id", baseTableName: "purchase_order_item", constraintName: "FK_qyodi27brvg62u48ffvniw5g4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-125") {
        addForeignKeyConstraint(baseColumnNames: "bank_account_id", baseTableName: "purchase_order", constraintName: "FK_r9qv2bxe3le52lt36ubnjramx", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-126") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_authorizations_id", baseTableName: "purchase_order_authorization", constraintName: "FK_ri9istm8vp3x6cop99vhxlhc5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-127") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "purchase_order_s3asset", constraintName: "FK_rjfla2m6jjrq4relh4idhb9c7", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-128") {
        addForeignKeyConstraint(baseColumnNames: "business_entity_id", baseTableName: "compose_name", constraintName: "FK_rjqgae44682dns471c7hweb8e", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "business_entity")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-129") {
        addForeignKeyConstraint(baseColumnNames: "company_telephones_id", baseTableName: "company_telephone", constraintName: "FK_s14mlklg07gxek8vc2qmg5yk4", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-130") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "client_link", constraintName: "FK_s2tgg8i7gbg9fgnkb7j05vhh1", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-131") {
        addForeignKeyConstraint(baseColumnNames: "telephone_id", baseTableName: "profile_telephone", constraintName: "FK_s6qu1iyo2xra9o42sj0vney", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "telephone")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-132") {
        addForeignKeyConstraint(baseColumnNames: "profile_documents_id", baseTableName: "profile_s3asset", constraintName: "FK_tf5ckj7ugmmbtd0nku9nm1hs5", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "profile")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-133") {
        addForeignKeyConstraint(baseColumnNames: "purchase_order_documents_id", baseTableName: "purchase_order_s3asset", constraintName: "FK_thyldems4h03uogsgduropc0i", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "purchase_order")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-134") {
        addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "cash_out_order", constraintName: "FK_tn08matxb1rdwy9879bbx2bsk", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "bank_account")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-135") {
        addForeignKeyConstraint(baseColumnNames: "s3asset_id", baseTableName: "profile_s3asset", constraintName: "FK_vevapj0ikfqe1s21iy1d9yyf", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "s3asset")
    }

    changeSet(author: "josdem (generated)", id: "1463151310620-136") {
        addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "loan_order", constraintName: "FK_wbaipjtxlax1ajhy32amn5pe", deferrable: "false", initiallyDeferred: "false", onDelete: "NO ACTION", onUpdate: "NO ACTION", referencedColumnNames: "id", referencedTableName: "company")
    }
}
