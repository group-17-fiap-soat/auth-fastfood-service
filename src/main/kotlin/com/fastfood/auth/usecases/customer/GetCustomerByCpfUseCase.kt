package com.fastfood.auth.usecases.customer

import com.fastfood.auth.adapters.gateways.AuthFeignGateway
import com.fastfood.auth.common.exception.InvalidCustomerDataException
import com.fastfood.auth.common.utils.Validator
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Service

@Service
class GetCustomerByCpfUseCase(
    private val authFeignGateway: AuthFeignGateway
) {

    fun execute(cpf: String): Customer? {
        if (!Validator.isValidCpf(cpf)) {
            throw InvalidCustomerDataException("CPF inválido.")
        }
        return authFeignGateway.getCustomerByCpf(cpf)
    }
}