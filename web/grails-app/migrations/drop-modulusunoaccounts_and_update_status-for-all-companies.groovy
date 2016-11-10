databaseChangeLog = {

  changeSet(author: "temoc", id: "update_status_to_VALIDATE_for_all_companies_accepted") {
    sql(""" update company set status='VALIDATE' where status='ACCEPTED' """);
  }

  changeSet(author: "temoc", id: "deleting_records_from_modulus_uno_account") {
    sql(""" delete from modulus_uno_account """);
  }

}
