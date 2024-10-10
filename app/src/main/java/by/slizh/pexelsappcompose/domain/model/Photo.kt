package by.slizh.pexelsappcompose.domain.model


data class Photo(
    val id: Int,
    val alt: String,
    val avgColor: String,
    val height: Int,
    val width: Int,
    val liked: Boolean,
    val photographer: String,
    val photographerId: Int,
    val photographerUrl: String,
    val src: SrcModel,
    val url: String
)