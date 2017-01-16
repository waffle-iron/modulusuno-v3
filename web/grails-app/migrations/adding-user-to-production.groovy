import grails.util.Environment

databaseChangeLog = {

  if(Environment.current == Environment.PRODUCTION){
  changeSet(author: "says33 (manual)",id: "create-user-to-production") {

    sql(""" INSERT INTO profile(version, email, last_name, mother_last_name, name) values (0,'alexis.brunel@techminds.com.mx', 'Brunel', 'Peralta', 'Alexis') """)
    sql(""" INSERT INTO user(enabled, password, profile_id, username, uuid, version, account_expired, account_locked, password_expired) values (true,'\$2a\$10\$/yexq4HcTmBLShsEjCpD/uRQ8SeE2dC3XNdKUWt67AWg7XfiwdCOm',2,'Atites', '21f8ca3ba2384f749cc076fe4esay482',0, false, false, false) """)
    sql(""" INSERT INTO user_role(user_id, role_id) values (2,10) """)

  }
  }
}
