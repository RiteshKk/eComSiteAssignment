package com.riteshkumar.nykaassignment.data.remote.dto

@kotlinx.serialization.Serializable
data class PriceRangeDto(
    val count: String?,
    val max: String?,
    val min: Int?
)