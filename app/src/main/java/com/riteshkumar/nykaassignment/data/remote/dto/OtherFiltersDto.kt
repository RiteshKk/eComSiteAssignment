package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class OtherFiltersDto(
    @SerializedName("top_brand")
    val topBrand: List<TopBrandDto>?,
    @SerializedName("top_old_brand")
    val topOldBrand: List<TopOldBrandDto>?
)