package br.com.moraesit.data.repository

import br.com.moraesit.data.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<Customer, UUID>