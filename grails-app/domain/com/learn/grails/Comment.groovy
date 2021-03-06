package com.learn.grails

class Comment {

    String comment
    Date dateCreated
    Date lastUpdated

    User user

    static belongsTo = [feedback: Feedback]

    static constraints = {
        comment (blank: false, nullable: false, size: 5..500)
        user (nullable: true)
    }

    @Override
    String toString() {
        if (comment?.size()>20) {
            return comment.substring(0,19)
        }
        return comment
    }
}
