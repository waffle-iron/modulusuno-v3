package com.modulus.uno

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProductController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def productList = []
    Long countProducts = 0
    params.sort = 'name'
    Company company = Company.get(session.company)
    productList = Product.findAllByCompany(company, params)
    countProducts = Product.countByCompany(company)
    respond productList, model:[productList:productList, productCount: countProducts]
  }

  def show(Product product) {
    respond product
  }

  def create() {
    respond new Product(params)
  }

  @Transactional
  def save(ProductCommand command) {
    if (command == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    def product = command.createProduct()
    product.company = Company.get(session.company)

   if (!product.validate()){
      render view:'create', model:[product:product]
      return
    }

    if (product.hasErrors()) {
      transactionStatus.setRollbackOnly()
      render product.errors, view:'create'
      return
    }

    product.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'product.created', args: [:])
        redirect action:'show', id: product.id, params: params
      }
      '*' { respond product, [status: CREATED] }
    }
  }

  def edit(Product product) {
    respond product
  }

  @Transactional
  def update(ProductCommand command) {
    if (command == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }
    def product = Product.findById(params.id)
    def company = product.company
    product.properties = command.createProduct().properties
    product.company = company
    if (product.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond product.errors, view:'edit'
      return
    }
    product.save flush:true
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'product.updated', args: [:])
        redirect product
      }
      '*'{ respond product, [status: OK] }
    }
  }

  @Transactional
  def delete(Product product) {

    if (product == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    product.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'product.label', default: 'Product'), product.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'product.label', default: 'Product'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }

}
