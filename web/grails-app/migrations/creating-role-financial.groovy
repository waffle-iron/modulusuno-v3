databaseChangeLog = {

  changeSet(author: "temoc (manual)",id: "creating-role-financial") {

    preConditions(onFail: 'MARK_RAN', onFailMessage: 'Role already exists' ){
      sqlCheck(expectedResult: '0', "SELECT count(*) FROM role WHERE authority = 'ROLE_FINANCIAL'")
    }

    sql(""" INSERT INTO role(authority,version) values ('ROLE_FINANCIAL',0) """)

  }

}
