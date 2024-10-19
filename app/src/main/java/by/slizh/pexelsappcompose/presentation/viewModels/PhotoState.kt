package by.slizh.pexelsappcompose.presentation.viewModels

import by.slizh.pexelsappcompose.domain.model.FeaturedCollection
import by.slizh.pexelsappcompose.domain.model.Photo

data class PhotoState (
    val photos: List<Photo> = emptyList(),
    val featuredCollection: FeaturedCollection? = null,
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)