package com.fastfood.auth.adapters.gateways

import com.fastfood.auth.adapters.presenters.CustomerMapper
import com.fastfood.auth.common.interfaces.datasource.CustomerDataSource
import com.fastfood.auth.common.interfaces.gateway.CustomerGatewayInterface
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerGateway(
    val customerDataSource: CustomerDataSource
) : CustomerGatewayInterface {
    override fun save(entity: Customer): Customer {
        val customerEntity = CustomerMapper.toDAO(entity)
        return CustomerMapper.fromDaoToEntity(customerDataSource.save(customerEntity));
    }

    override fun findByCpf(cpf: String): Customer? {
        return customerDataSource.findByCpf(cpf)?.let(CustomerMapper::fromDaoToEntity )
    }

    override fun findById(id: UUID): Customer? {
        return customerDataSource.findById(id).orElse(null)?.let(CustomerMapper::fromDaoToEntity)
    }

    override fun findByEmail(email: String): Customer? {
        return customerDataSource.findFirstByEmail(email)?.let(CustomerMapper::fromDaoToEntity)
    }

}
