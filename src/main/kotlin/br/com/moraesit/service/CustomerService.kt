package br.com.moraesit.service

import br.com.moraesit.data.entity.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface CustomerService {

    fun search(customer: Customer, pageable: Pageable): Page<Customer>

    fun getById(customerId: UUID): Customer

    fun create(customer: Customer): Customer

    fun update(customerId: UUID, customer: Customer): Customer

    fun delete(customerId: UUID)
}