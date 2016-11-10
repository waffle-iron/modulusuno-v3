databaseChangeLog = {
  changeSet(author: "josdem", id: "20160718-1") {
    sql(""" update s3asset set local_url=REPLACE(local_url, 'http://', 'https://') """);
  }
}
