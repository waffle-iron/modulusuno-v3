databaseChangeLog = {

  changeSet(author: "says33 (manual)",id: "delete-relationship-into-user-and-role") {


    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_ADMIN')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_INTEGRADO')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_LEGAL_REPRESENTATIVE')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_ADMIN_IECCE')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_INTEGRADO_AUTORIZADOR')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_INTEGRADO_OPERADOR')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_OPERADOR_IECCE')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_EJECUTOR')  """)
    sql(""" DELETE FROM user_role where role_id = (select r.id from role as r where authority = 'ROLE_FINANCIAL')  """)

  }

}
