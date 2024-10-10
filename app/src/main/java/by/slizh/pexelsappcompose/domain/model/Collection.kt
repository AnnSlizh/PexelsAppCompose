package by.slizh.pexelsappcompose.domain.model

import com.google.gson.annotations.SerializedName

data class Collection(
    val id: Int,
    val title: String,
    val description: String,
    val private: Boolean,
    val mediaCount: Int,
    val photosCount: Int,
    val videosCount: Int
)