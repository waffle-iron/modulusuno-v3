package com.modulus.uno

class GroupNotification {

    String notificationId
    ArrayList<User> users
    String name

    static hasMany = [
      users:User
    ]

    static constraints = {
      notificationId blank:false
      users blank: false
      name blank: false
    }
}
