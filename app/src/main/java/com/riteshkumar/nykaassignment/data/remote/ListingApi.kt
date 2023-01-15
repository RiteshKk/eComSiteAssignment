package com.riteshkumar.nykaassignment.data.remote

import com.riteshkumar.nykaassignment.data.remote.dto.ListingResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ListingApi {

    @GET("products")
    suspend fun getListingData(
        @Query("page") page: Int
    ): ListingResponseDto
}