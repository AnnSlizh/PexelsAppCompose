package by.slizh.pexelsappcompose.presentation.viewModels.photoDetails

import by.slizh.pexelsappcompose.domain.model.Photo

sealed class PhotoDetailsEvent {
    data class LoadPhotoById(val photoId: Int) : PhotoDetailsEvent()
    data class CheckBookmarkStatus(val photoId: Int) : PhotoDetailsEvent()
    data class DeleteBookmark(val photo: Photo) : PhotoDetailsEvent()
    object BookmarkPhoto : PhotoDetailsEvent()
}