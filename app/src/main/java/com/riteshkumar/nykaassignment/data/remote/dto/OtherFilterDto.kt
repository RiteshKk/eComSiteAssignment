package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class OtherFilterDto(
    val key: String?,
    val title: String?,
    @SerializedName("tracking_group")
    val trackingGroup: String?,
    val type: String?
)