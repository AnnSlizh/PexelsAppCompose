package by.slizh.pexelsappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoListDto (
    val photos: List<PhotoDto>,
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("total_results")
    val totalResults: Int
)