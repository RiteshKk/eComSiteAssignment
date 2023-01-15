package com.riteshkumar.nykaassignment.data.remote.dto

import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class ResponseDto(
    @SerializedName("android_landing_url")
    val androidLandingUrl: String? = null,
    @SerializedName("app_sorting")
    val appSorting: String? = null,
    @SerializedName("art_content")
    val artContent: String? = null,
    @SerializedName("art_link_text")
    val artLinkText: String? = null,
    @SerializedName("art_pos")
    val artPos: String? = null,
    @SerializedName("art_title")
    val artTitle: String? = null,
    @SerializedName("art_url")
    val artUrl: String? = null,
    @SerializedName("auth_certificate")
    val authCertificate: String? = null,
    @SerializedName("banner_image")
    val bannerImage: String? = null,
    @SerializedName("banner_video")
    val bannerVideo: String? = null,
    @SerializedName("banner_video_image")
    val bannerVideoImage: String? = null,
    @SerializedName("category_name")
    val categoryName: String? = null,
    @SerializedName("child_categories")
    val childCategories: String? = null,
    @SerializedName("desktop_tip_tile")
    val desktopTipTile: String? = null,
    @SerializedName("filter_keys")
    val filterKeys: FilterKeysDto? = null,
    val filters: FiltersDto? = null,
    @SerializedName("font_color")
    val fontColor: String? = null,
    @SerializedName("ios_landing_url")
    val iosLandingUrl: String? = null,
    val isNewApi: Boolean? = null,
    val level: Int? = null,
    val namespace: List<String>? = null,
    val offset: Int? = null,
    @SerializedName("other_filters")
    val otherFilters: OtherFiltersDto? = null,
    @SerializedName("product_count")
    val productCount: Int? = null,
    val products: List<ProductDto>? = null,
    val query: String? = null,
    @SerializedName("size_chart_image")
    val sizeChartImage: String? = null,
    val sort: String? = null,
    @SerializedName("stop_further_call")
    val stopFurtherCall: Int? = null,
    @SerializedName("tip_tile")
    val tipTile: String? = null,
    val title: String? = null,
    @SerializedName("total_found")
    val totalFound: Int? = null,
    val type: String? = null,
    val url: String? = null
)