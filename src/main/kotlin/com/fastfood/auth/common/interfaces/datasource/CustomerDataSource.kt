package com.fastfood.auth.common.interfaces.datasource

import com.fastfood.auth.common.dao.CustomerDAO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerDataSource : JpaRepository<CustomerDAO, UUID> {
    fun findByCpf(cpf: String): CustomerDAO?
    fun findFirstByEmail(email: String): CustomerDAO?
}