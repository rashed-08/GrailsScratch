package com.learn.grails

import grails.transaction.Transactional

@Transactional(readOnly = true)
class FeedbackController {

    static  allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def scaffold = Feedback

    def show(Feedback feedbackInstance) {
        response feedbackInstance
    }

    def created() {
        response new Feedback(params)
    }

    @Transactional
    def save(Feedback feedbackInstance) {
        if (feedbackInstance == null) {
            notFound()
            return
        }

        if (feedbackInstance.hasErrors()) {
            response feedbackInstance.errors, view: 'create'
            return
        }

        feedbackInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'feedback.label', default: 'Feedback'), feedbackInstance.id])
                redirect feedbackInstance
            }
            '*' {
                response feedbackInstance, [status: CREATED]
            }
        }
    }

    def edit(Feedback feedback) {
        response feedback
    }

    @Transactional
    def update(Feedback feedback) {
        if (feedback == null) {
            notFound()
            return
        }

        if (feedback.hasErrors()) {
            response feedbackInstance.errors, view: 'edit'
            return
        }

        feedback.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.update.message', args: [message(code: 'Feedback.label', default: 'Feedback'), feedback.id])
                redirect feedback
            }
            '*' {
                response feedback, [status: OK]
            }
        }
    }

    @Transactional
    def delete(Feedback feedback) {
        if (feedback == null) {
            notFound()
            return
        }

        feedback.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Feedback.label', default: 'Feedback'), feedback.id])
                redirect action: "index", method: "GET"
            }
            '*' {
                response feedback, [status: OK]
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'feedback.label', default: 'Feedback'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
