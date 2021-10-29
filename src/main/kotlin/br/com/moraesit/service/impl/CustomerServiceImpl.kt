package br.com.moraesit.service.impl

import br.com.moraesit.data.entity.Customer
import br.com.moraesit.data.repository.CustomerRepository
import br.com.moraesit.service.CustomerService
import org.springframework.beans.BeanUtils
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class CustomerServiceImpl(
        private val customerRepository: CustomerRepository
) : CustomerService {

    @Cacheable(cacheNames = ["customerCache"])
    override fun search(customer: Customer, pageable: Pageable): Page<Customer> {
        return customerRepository.findAll(Example.of(customer, ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)), pageable)
    }

    @Cacheable(cacheNames = ["customerCache"], key = "#customerId")
    override fun getById(customerId: UUID): Customer {
        return customerRepository.findById(customerId)
                .orElseThrow { EntityNotFoundException("Customer not found.") }
    }

    @Transactional
    @CacheEvict(cacheNames = ["customerCache"], allEntries = true)
    override fun create(customer: Customer) = customerRepository.save(customer)

    @Transactional
    @CachePut(cacheNames = ["customerCache"], key = "#customerId")
    override fun update(customerId: UUID, customer: Customer): Customer {
        val currentCustomer = getById(customerId)
        BeanUtils.copyProperties(customer, currentCustomer, "id")
        return customerRepository.save(currentCustomer)
    }

    @Transactional
    @CacheEvict(cacheNames = ["customerCache"], key = "#customerId")
    override fun delete(customerId: UUID) {
        try {
            customerRepository.deleteById(customerId)
            customerRepository.flush()
        } catch (ex: EmptyResultDataAccessException) {
            throw EntityNotFoundException("Customer not found.")
        }
    }
}