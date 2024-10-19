package by.slizh.pexelsappcompose.domain.model

data class FeaturedCollection(
    val collections: List<CollectionModel>,
    val page: Int,
    val perPage: Int,
    val totalResults: Int
)
