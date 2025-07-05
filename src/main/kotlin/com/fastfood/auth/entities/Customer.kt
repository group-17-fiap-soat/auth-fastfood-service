package com.fastfood.auth.entities

import java.time.OffsetDateTime
import java.util.*

data class Customer(
    val id: UUID? = null,
    val cpf: String? = null,
    val name: String? = null,
    val email: String? = null,
    val token: String? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null
)
