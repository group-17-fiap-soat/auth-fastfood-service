
package com.fastfood.auth.usecases.customer

import com.fastfood.auth.common.exception.InvalidCustomerDataException
import com.fastfood.auth.common.interfaces.gateway.CustomerGatewayInterface
import com.fastfood.auth.entities.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class CreateCustomerUseCaseTest {

    private val customerGateway: CustomerGatewayInterface = mock()
    private val useCase = CreateCustomerUseCase(customerGateway)

    @Test
    fun `should create customer when data is valid`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = "12345678901")
        whenever(customerGateway.findByCpf(any())).thenReturn(null)
        whenever(customerGateway.findByEmail(any())).thenReturn(null)
        whenever(customerGateway.save(any())).thenReturn(customer)

        val result = useCase.execute(customer)

        assertEquals(customer, result)
        verify(customerGateway).save(customer)
    }

    @Test
    fun `should throw exception for invalid CPF`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = "123")
        
        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(customer) }
        
        assertEquals("CPF inválido.", ex.message)
    }

    @Test
    fun `should throw exception for invalid email`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "invalid-email", cpf = "12345678901")

        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(customer) }

        assertEquals("Email inválido.", ex.message)
    }

    @Test
    fun `should throw exception if CPF is already registered`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = "12345678901")
        whenever(customerGateway.findByCpf(customer.cpf!!)).thenReturn(customer)

        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(customer) }

        assertEquals("CPF já cadastrado.", ex.message)
    }

    @Test
    fun `should throw exception if email is already registered`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = "12345678901")
        whenever(customerGateway.findByCpf(customer.cpf!!)).thenReturn(null)
        whenever(customerGateway.findByEmail(customer.email!!)).thenReturn(customer)

        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(customer) }

        assertEquals("Email já cadastrado.", ex.message)
    }
    
    @Test
    fun `should throw exception when CPF is null`() {
        val customer = Customer(id = UUID.randomUUID(), name = "John Doe", email = "john.doe@example.com", cpf = null)

        val ex = assertThrows<InvalidCustomerDataException> { useCase.execute(customer) }

        assertEquals("CPF tem que ser preenchido.", ex.message)
    }
}
