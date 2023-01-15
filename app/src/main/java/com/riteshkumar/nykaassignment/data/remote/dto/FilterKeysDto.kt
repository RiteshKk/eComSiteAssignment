package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class FilterKeysDto(
    val filters: List<FilterDto>?,
    @SerializedName("other_filters")
    val otherFilters: List<OtherFilterDto>?
)