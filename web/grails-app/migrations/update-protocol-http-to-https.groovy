databaseChangeLog = {
  changeSet(author: "josdem", id: "20160718-2") {
    sql(""" update s3asset set protocol='https://' """);
  }
}
