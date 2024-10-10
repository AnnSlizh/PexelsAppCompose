package by.slizh.pexelsappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Collection(
    val id: Int,
    val title: String,
    val description: String,
    val private: Boolean,
    @SerializedName("media_count")
    val mediaCount: Int,
    @SerializedName("photos_count")
    val photosCount: Int,
    @SerializedName("videos_count")
    val videosCount: Int
)
