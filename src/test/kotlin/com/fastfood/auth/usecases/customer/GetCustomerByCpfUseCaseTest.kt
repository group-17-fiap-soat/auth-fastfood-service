
package com.fastfood.auth.usecases.customer

import com.fastfood.auth.common.exception.InvalidCustomerDataException
import com.fastfood.auth.adapters.gateways.AuthFeignGateway
import com.fastfood.auth.entities.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class GetCustomerByCpfUseCaseTest {

    private val authFeignGateway: AuthFeignGateway = mock()
    private val useCase = GetCustomerByCpfUseCase(authFeignGateway)

    @Test
    fun `should return customer when CPF is valid and customer exists`() {
        val cpf = "12345678901"
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = cpf)
        whenever(authFeignGateway.getCustomerByCpf(cpf)).thenReturn(customer)

        val result = useCase.execute(cpf)

        assertEquals(customer, result)
        verify(authFeignGateway).getCustomerByCpf(cpf)
    }

    @Test
    fun `should return null when customer does not exist`() {
        val cpf = "12345678901"
        whenever(authFeignGateway.getCustomerByCpf(cpf)).thenReturn(null)

        val result = useCase.execute(cpf)

        assertNull(result)
        verify(authFeignGateway).getCustomerByCpf(cpf)
    }

    @Test
    fun `should throw exception for invalid CPF`() {
        val cpf = "123"

        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(cpf) }

        assertEquals("CPF inválido.", ex.message)
    }
}
