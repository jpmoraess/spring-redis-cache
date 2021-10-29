package br.com.moraesit.api.v1.controller

import br.com.moraesit.api.v1.model.CustomerModel
import br.com.moraesit.api.v1.model.toModel
import br.com.moraesit.data.entity.Customer
import br.com.moraesit.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/customers")
class CustomerController(
        private val customerService: CustomerService
) {

    @GetMapping
    fun search(customer: Customer, pageable: Pageable): Page<CustomerModel> {
        return customerService.search(customer, pageable).map { it.toModel() }
    }

    @GetMapping("/{customerId}")
    fun getById(@PathVariable customerId: UUID): CustomerModel {
        return customerService.getById(customerId).toModel()!!
    }

    @PostMapping
    fun create(@RequestBody customer: Customer): ResponseEntity<CustomerModel> {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.create(customer).toModel())
    }

    @PutMapping("/{customerId}")
    fun update(@PathVariable customerId: UUID, @RequestBody customer: Customer): CustomerModel {
        return customerService.update(customerId, customer).toModel()!!
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable customerId: UUID) = customerService.delete(customerId)
}