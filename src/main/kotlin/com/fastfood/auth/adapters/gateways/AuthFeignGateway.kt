package com.fastfood.auth.adapters.gateways

import com.fastfood.auth.adapters.presenters.CustomerMapper
import com.fastfood.auth.common.dto.request.AuthFeignRequest
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Component
import com.fastfood.auth.adapters.gateways.feign.AuthFeignGatewayInterface

@Component
class AuthFeignGateway(
    private val authFeignGatewayInterface: AuthFeignGatewayInterface,
) {

    fun getCustomerByCpf(cpf: String): Customer {
        val response = authFeignGatewayInterface.authenticate(AuthFeignRequest(cpf))
        return CustomerMapper.fromAuthFeignResponse(response).copy(cpf = cpf)
    }
}
