package by.slizh.pexelsappcompose.presentation.viewModels.bookmarks

import by.slizh.pexelsappcompose.domain.model.Photo

data class BookmarksState(
    val bookmarks: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
