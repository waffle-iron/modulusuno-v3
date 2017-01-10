databaseChangeLog = {

  changeSet(author: "says33 (manual)",id: "delete-roles-in-m1") {

    sql(""" DELETE FROM role where authority = 'ROLE_ADMIN' """)
    sql(""" DELETE FROM role where authority = 'ROLE_INTEGRADO' """)
    sql(""" DELETE FROM role where authority = 'ROLE_LEGAL_REPRESENTATIVE' """)
    sql(""" DELETE FROM role where authority = 'ROLE_ADMIN_IECCE' """)
    sql(""" DELETE FROM role where authority = 'ROLE_INTEGRADO_AUTORIZADOR' """)
    sql(""" DELETE FROM role where authority = 'ROLE_INTEGRADO_OPERADOR' """)
    sql(""" DELETE FROM role where authority = 'ROLE_OPERADOR_IECCE' """)
    sql(""" DELETE FROM role where authority = 'ROLE_EJECUTOR' """)
    sql(""" DELETE FROM role where authority = 'ROLE_FINANCIAL' """)

  }
}
