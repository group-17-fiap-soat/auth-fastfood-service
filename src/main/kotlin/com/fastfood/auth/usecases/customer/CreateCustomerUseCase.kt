package tech.challenge.fastfood.fastfood.usecases.customer

import com.fastfood.auth.common.exception.InvalidCustomerDataException
import com.fastfood.auth.common.interfaces.gateway.CustomerGatewayInterface
import com.fastfood.auth.common.utils.Validator
import com.fastfood.auth.entities.Customer
import org.springframework.stereotype.Service

@Service
class CreateCustomerUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {
    fun execute(customer: Customer): Customer {
        validateCustomer(customer)

        return customerGatewayInterface.save(customer)
    }

    private fun isCpfAlreadyRegistered(cpf: String): Boolean {
        return customerGatewayInterface.findByCpf(cpf) != null
    }

    private fun isEmailAlreadyRegistered(email: String): Boolean {
        return customerGatewayInterface.findByEmail(email) != null
    }

    private fun validateCustomer(customer: Customer) {
        val cpf = checkNotNull(customer.cpf) {
            throw InvalidCustomerDataException("CPF tem que ser preenchido.")
        }
        if (customer.email!= null && !Validator.isValidEmail(customer.email))
            throw InvalidCustomerDataException("Email inválido.")

        if (!Validator.isValidCpf(cpf))
            throw InvalidCustomerDataException("CPF inválido.")

        if (isCpfAlreadyRegistered(cpf))
            throw InvalidCustomerDataException("CPF já cadastrado.")

        if (isEmailAlreadyRegistered(customer.email!!))
            throw InvalidCustomerDataException("Email já cadastrado.")
    }
}