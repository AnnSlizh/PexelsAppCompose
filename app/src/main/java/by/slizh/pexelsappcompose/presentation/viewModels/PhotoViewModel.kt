package by.slizh.pexelsappcompose.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.repository.PhotoRepository
import by.slizh.pexelsappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(PhotoState())
    val state: StateFlow<PhotoState> = _state.asStateFlow()

    init {
        onEvent(PhotoEvent.LoadCuratedPhotos)
        onEvent(PhotoEvent.LoadFeaturedCollections)
    }

    fun onEvent(event: PhotoEvent) {
        when (event) {
            is PhotoEvent.LoadCuratedPhotos -> getCuratedPhotos()
            is PhotoEvent.SearchPhotos -> searchPhotos(event.query)
            //  is PhotoEvent.LoadPhotoById -> getPhotoById(event.photoId)
            is PhotoEvent.LoadFeaturedCollections -> getFeaturedCollections()
            is PhotoEvent.SelectFeatureCollection -> selectFeaturedCollection(event.query)

        }
    }

    private fun getCuratedPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getCuratedPhotoList().collect { result -> getSearchPhotoResult(result) }
        }
    }

    private fun searchPhotos(query: String) {
        _state.value = _state.value.copy(query = query)
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getSearchPhotoList(query)
                .collect { result -> getSearchPhotoResult(result) }
        }
    }

//    private fun getPhotoById(photoId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            photoRepository.getPhotoById(photoId).collect{ result ->  }
//        }
//    }

    private fun getFeaturedCollections() {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.getFeaturedCollections().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)

                    is Resource.Success -> _state.value =
                        _state.value.copy(featuredCollection = result.data, isLoading = false)

                    is Resource.Error -> _state.value =
                        _state.value.copy(error = result.message, isLoading = false)
                }

            }
        }
    }

    private fun selectFeaturedCollection(query: String) {
        _state.value = _state.value.copy(query = query)
        searchPhotos(query)
    }

    private fun getSearchPhotoResult(result: Resource<List<Photo>>) {
        when (result) {
            is Resource.Loading -> _state.value = _state.value.copy(isLoading = result.isLoading)
            is Resource.Success -> _state.value =
                _state.value.copy(photos = result.data ?: emptyList(), isLoading = false)
            is Resource.Error -> _state.value = _state.value.copy(error = result.message, isLoading = false)
        }
    }
}