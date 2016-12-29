import grails.util.Environment

databaseChangeLog = {

  if(Environment.current == Environment.PRODUCTION){
  changeSet(author: "says33 (manual)",id: "create-user-admin-production") {

    sql(""" INSERT INTO profile(version, email, last_name, mother_last_name, name) values (0,'contacto@microquasar.net', 'Garcia', 'Microquasar', 'Guadalupe') """)
    sql(""" INSERT INTO user(enabled, password, profile_id, username, uuid, version, account_expired, account_locked, password_expired) values (true,'\$2a\$10\$c4FiWA5kjemcm95iH/qul.4QVGsSRmWNEx7y7LPkOBqZQtE2yJFq.',1,'Operadmin', '21f8ca3ba2384f749cc076fe4ebee482',0, false, false, false) """)
    sql(""" INSERT INTO user_role(user_id, role_id) values (1,1) """)

  }
  }
}
