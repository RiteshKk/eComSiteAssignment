package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class PriceDto(
    @SerializedName("max_price")
    val maxPrice: Double?,
    @SerializedName("min_price")
    val minPrice: Int?,
    @SerializedName("price_range")
    val priceRange: List<PriceRangeDto>?
)