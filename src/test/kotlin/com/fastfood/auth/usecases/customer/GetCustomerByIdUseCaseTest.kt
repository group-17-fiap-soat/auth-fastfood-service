
package com.fastfood.auth.usecases.customer

import com.fastfood.auth.common.interfaces.gateway.CustomerGatewayInterface
import com.fastfood.auth.entities.Customer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class GetCustomerByIdUseCaseTest {

    private val customerGateway: CustomerGatewayInterface = mock()
    private val useCase = GetCustomerByIdUseCase(customerGateway)

    @Test
    fun `should return customer when ID exists`() {
        val id = UUID.randomUUID()
        val customer = Customer(id = id, name = "John Doe", email = "john.doe@example.com", cpf = "12345678901")
        whenever(customerGateway.findById(id)).thenReturn(customer)

        val result = useCase.execute(id)

        assertEquals(customer, result)
        verify(customerGateway).findById(id)
    }

    @Test
    fun `should return null when ID does not exist`() {
        val id = UUID.randomUUID()
        whenever(customerGateway.findById(id)).thenReturn(null)

        val result = useCase.execute(id)

        assertNull(result)
        verify(customerGateway).findById(id)
    }
}
