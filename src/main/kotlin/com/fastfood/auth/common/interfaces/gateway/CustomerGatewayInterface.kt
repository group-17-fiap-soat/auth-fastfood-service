package com.fastfood.auth.common.interfaces.gateway;

import com.fastfood.auth.entities.Customer
import java.util.*

interface CustomerGatewayInterface {
    fun save(entity: Customer): Customer
    fun findByCpf(cpf: String): Customer?
    fun findByEmail(email: String): Customer?
    fun findById(id: UUID): Customer?
}
