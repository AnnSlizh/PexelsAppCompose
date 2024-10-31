package by.slizh.pexelsappcompose.presentation.viewModels.photoDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.repository.BookmarkRepository
import by.slizh.pexelsappcompose.domain.repository.PhotoRepository
import by.slizh.pexelsappcompose.util.Resource
import by.slizh.pexelsappcompose.util.photoDownloader.Downloader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailsViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val bookmarkRepository: BookmarkRepository,
    savedStateHandle: SavedStateHandle,
    val downloader: Downloader
) : ViewModel() {

    private val _state = MutableStateFlow(PhotoDetailsState())
    val state: StateFlow<PhotoDetailsState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("photoId")?.let { photoId ->
            onEvent(PhotoDetailsEvent.LoadPhotoById(photoId = photoId))
        }
    }

    fun onEvent(event: PhotoDetailsEvent) {
        when (event) {
            is PhotoDetailsEvent.LoadPhotoById -> loadPhotoById(event.photoId)
            is PhotoDetailsEvent.BookmarkPhoto -> bookmarkPhoto()
            is PhotoDetailsEvent.CheckBookmarkStatus -> checkBookmarkStatus(event.photoId)
            is PhotoDetailsEvent.DeleteBookmark -> deleteBookmark(event.photo)
        }
    }

    private fun loadPhotoById(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getPhotoById(photoId).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)

                    is Resource.Success -> _state.value =
                        _state.value.copy(photo = result.data, isLoading = false)

                    is Resource.Error -> _state.value =
                        _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }

    private fun bookmarkPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            state.value.photo?.let { photo ->
                bookmarkRepository.insertBookmark(photo)
                _state.value = _state.value.copy(isBookmarked = true)
            }
        }
    }

    private fun checkBookmarkStatus(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.getBookmarkById(photoId).collect { result ->
                when (result) {
                    is Resource.Success -> _state.value = _state.value.copy(isBookmarked = true)
                    is Resource.Error -> _state.value = _state.value.copy(isBookmarked = false)
                    else -> Unit
                }
            }
        }
    }

    private fun deleteBookmark(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            bookmarkRepository.deleteBookmark(photo)
            _state.value = _state.value.copy(isBookmarked = false)
        }
    }
}