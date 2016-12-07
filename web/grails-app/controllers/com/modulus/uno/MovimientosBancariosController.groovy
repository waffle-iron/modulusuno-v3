package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MovimientosBancariosController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
      def bankAccountsOfCompany = Company.findById(session.company.toLong()).banksAccounts
      [bankAccountsOfCompany:bankAccountsOfCompany]
    }

    def show(BankAccount bankAccount) {
      def movimientosBancarios = MovimientosBancarios.findAllByCuenta(bankAccount)
      [movimientosBancarios:movimientosBancarios]
    }

    def create() {
      def bankAccount = BankAccount.findById(params.id)
        respond new MovimientosBancarios(cuenta:bankAccount)
    }

    @Transactional
    def save(MovimientosBancariosCommand command) {

      def movimientosBancarios = command.createObject()
        movimientosBancarios.cuenta = BankAccount.findById(params.cuenta)
        if (movimientosBancarios.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond movimientosBancarios.errors, view:'create'
            return
        }

        movimientosBancarios.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios'), movimientosBancarios.id])
                redirect movimientosBancarios
            }
            '*' { respond movimientosBancarios, [status: CREATED] }
        }
    }

    def edit(MovimientosBancarios movimientosBancarios) {
        respond movimientosBancarios
    }

    @Transactional
    def update(MovimientosBancarios movimientosBancarios) {
        if (movimientosBancarios == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (movimientosBancarios.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond movimientosBancarios.errors, view:'edit'
            return
        }

        movimientosBancarios.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios'), movimientosBancarios.id])
                redirect movimientosBancarios
            }
            '*'{ respond movimientosBancarios, [status: OK] }
        }
    }

    @Transactional
    def delete(MovimientosBancarios movimientosBancarios) {

        if (movimientosBancarios == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        movimientosBancarios.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios'), movimientosBancarios.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'movimientosBancarios.label', default: 'MovimientosBancarios'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
