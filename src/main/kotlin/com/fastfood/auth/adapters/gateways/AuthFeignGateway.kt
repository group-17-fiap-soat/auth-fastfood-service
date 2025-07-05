package com.fastfood.auth.adapters.gateways

import com.fasterxml.jackson.databind.ObjectMapper
import com.fastfood.auth.adapters.presenters.CustomerMapper
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.gateways.feign.AuthFeignGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.dto.request.AuthFeignRequest
import tech.challenge.fastfood.fastfood.entities.Customer

@Component
class AuthFeignGateway(
    private val authFeignGatewayInterface: AuthFeignGatewayInterface,
) {

    fun getCustomerByCpf(cpf: String): Customer {
        val response = authFeignGatewayInterface.authenticate(AuthFeignRequest(cpf))

        return CustomerMapper.fromAuthFeignResponse(response).copy(cpf = cpf)
    }
}
