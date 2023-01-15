package com.riteshkumar.nykaassignment.domain.model

data class Product(
    val brandIds: String,
    val brandName: String,
    val buttonText: String,
    val categoryIds: String,
    val discount: Int,
    val finalPrice: Int,
    val id: String,
    val imageUrl: String,
    val name: String,
    val price: Int,
    val quantity: Int,
    val rating: Double,
    val ratingCount: Int,
    val showWishlistButton: Int,
    val sku: String,
    val type: String
)
