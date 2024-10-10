package by.slizh.pexelsappcompose.domain.model

import com.google.gson.annotations.SerializedName

data class FeaturedCollection(
    val collections: List<Collection>,
    val page: Int,
    val perPage: Int,
    val totalResults: Int
)
