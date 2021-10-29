package br.com.moraesit.api.v1.model

import br.com.moraesit.data.entity.Customer
import java.util.*

class CustomerModel(var id: UUID? = null, var name: String? = null, var email: String? = null)

fun Customer?.toModel(): CustomerModel? {
    if (this == null)
        return null
    return CustomerModel(id, name, email)
}