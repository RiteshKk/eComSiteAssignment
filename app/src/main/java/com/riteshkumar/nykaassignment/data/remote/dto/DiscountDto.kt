package com.riteshkumar.nykaassignment.data.remote.dto

@kotlinx.serialization.Serializable
data class DiscountDto(
    val count: String?,
    val name: String?,
    val value: String?
)