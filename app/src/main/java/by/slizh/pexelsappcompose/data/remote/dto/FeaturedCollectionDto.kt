package by.slizh.pexelsappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FeaturedCollectionDto (
    val collections: List<Collection>,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_results")
    val totalResults: Int
)