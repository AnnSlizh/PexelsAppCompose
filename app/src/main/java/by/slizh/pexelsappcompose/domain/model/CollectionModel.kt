package by.slizh.pexelsappcompose.domain.model

data class CollectionModel(
    val id: Int,
    val title: String,
    val description: String,
    val private: Boolean,
    val mediaCount: Int,
    val photosCount: Int,
    val videosCount: Int
)
