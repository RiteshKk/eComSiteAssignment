package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.riteshkumar.nykaassignment.domain.model.Product

@kotlinx.serialization.Serializable
data class ProductDto(
    @SerializedName("brand_ids")
    val brandIds: String? = null,
    @SerializedName("brand_name")
    val brandName: String? = null,
    @SerializedName("button_text")
    val buttonText: String? = null,
    @SerializedName("catalog_tag")
    val catalogTag: List<String>? = null,
    @SerializedName("category_ids")
    val categoryIds: String? = null,
    val discount: Int? = null,
    @SerializedName("dynamic_tags")
    val dynamicTags: List<String>? = null,
    val expdt: String? = null,
    @SerializedName("explore_more")
    val exploreMore: String? = null,
    val fbn: Int? = null,
    @SerializedName("final_price")
    val finalPrice: Int,
    @SerializedName("from_gludo")
    val fromGludo: Int? = null,
    @SerializedName("gludo_stock")
    val gludoStock: Boolean? = null,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("is_backorder")
    val isBackorder: Int? = null,
    @SerializedName("is_kit_combo")
    val isKitCombo: String? = null,
    @SerializedName("is_lux")
    val isLux: Int? = null,
    @SerializedName("is_saleable")
    val isSaleable: Boolean? = null,
    @SerializedName("mrp_freeze")
    val mrpFreeze: String? = null,
    val name: String? = null,
    @SerializedName("object_type")
    val objectType: String? = null,
    val offers: String? = null,
    @SerializedName("pack_size")
    val packSize: String? = null,
    val price: Int,
    @SerializedName("primary_categories")
    val primaryCategories: HashMap<String, PrimaryCategory>? = null,
    @SerializedName("pro_flag")
    val proFlag: Int? = null,
    val quantity: Int? = null,
    val rating: Double? = null,
    @SerializedName("rating_count")
    val ratingCount: Int? = null,
    @SerializedName("show_wishlist_button")
    val showWishlistButton: Int? = null,
    val sku: String? = null,
    val slug: String? = null,
    val type: String? = null
)

fun ProductDto.toProduct() = Product(
    brandIds = this.brandIds ?: "",
    brandName = this.brandName ?: "",
    buttonText = this.buttonText ?: "",
    categoryIds = this.categoryIds ?: "",
    discount = this.discount ?: 0,
    finalPrice = this.finalPrice,
    id = this.id,
    imageUrl = this.imageUrl ?: "",
    name = this.name ?: "",
    price = this.price,
    quantity = this.quantity ?: 0,
    rating = this.rating ?: 0.0,
    ratingCount = this.ratingCount ?: 0,
    showWishlistButton = this.showWishlistButton ?: 0,
    sku = this.sku ?: "",
    type = this.type ?: ""
)