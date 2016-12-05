databaseChangeLog = {

  changeSet(author: "says33 (manual)",id: "creating-role-ejecutor") {

    preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
      sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_EJECUTOR'")
    }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_EJECUTOR',0) """)

  }

}
