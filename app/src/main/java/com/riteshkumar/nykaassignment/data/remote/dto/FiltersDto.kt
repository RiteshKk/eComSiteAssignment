package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

typealias TopBrandDto = BrandDto
typealias TopOldBrandDto = BrandDto
typealias StarRatingDto = BrandDto

@kotlinx.serialization.Serializable
data class FiltersDto(
    val brand: List<BrandDto>?,
    val category: List<CategoryDto>?,
    val discount: List<DiscountDto>?,
    val price: PriceDto?,
    @SerializedName("star_rating")
    val starRating: List<StarRatingDto>?
)