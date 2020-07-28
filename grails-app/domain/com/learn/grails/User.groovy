package com.learn.grails

class User {

    String name
    String email
    String webpage

    static constraints = {
        name (blank: false, nullable: false, size: 2..20, matches: "[a-zA-Z1-9_]+")
        email (email: true)
        webpage (url: true)
    }

    @Override
    String toString() {
        return name
    }
}
