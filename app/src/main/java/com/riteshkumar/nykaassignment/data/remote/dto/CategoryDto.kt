package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class CategoryDto(
    @SerializedName("category_id")
    val categoryId: String?,
    val count: String?,
    val depth: String?,
    @SerializedName("include_in_menu")
    val includeInMenu: String?,
    val lft: String?,
    val name: String?,
    val parent_id: String?,
    val position: String?,
    val rgt: String?
)