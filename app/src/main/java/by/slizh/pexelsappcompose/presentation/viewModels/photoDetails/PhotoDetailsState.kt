package by.slizh.pexelsappcompose.presentation.viewModels.photoDetails

import by.slizh.pexelsappcompose.domain.model.Photo

data class PhotoDetailsState(
    val isLoading: Boolean = false,
    val photo: Photo? = null,
    val isBookmarked: Boolean = false,
    val error: String? = null
)