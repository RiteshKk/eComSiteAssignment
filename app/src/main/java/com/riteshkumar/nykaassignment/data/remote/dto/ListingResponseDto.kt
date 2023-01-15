package com.riteshkumar.nykaassignment.data.remote.dto

@kotlinx.serialization.Serializable
data class ListingResponseDto(
    val message: String?,
    val response: ResponseDto?,
    val status: String?,
    val type: String?
)