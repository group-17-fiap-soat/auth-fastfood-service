package com.fastfood.auth.usecases.customer

import com.fastfood.auth.common.interfaces.gateway.CustomerGatewayInterface
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetCustomerByIdUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {

    fun execute(id: UUID): Customer? {
        return customerGatewayInterface.findById(id)
    }
}