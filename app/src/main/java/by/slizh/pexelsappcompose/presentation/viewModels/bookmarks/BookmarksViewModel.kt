package by.slizh.pexelsappcompose.presentation.viewModels.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.slizh.pexelsappcompose.domain.repository.BookmarkRepository
import by.slizh.pexelsappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: BookmarkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BookmarksState())
    val state: StateFlow<BookmarksState> = _state.asStateFlow()

    init {
        onEvent(BookmarksEvent.LoadBookmarks)
    }

    fun onEvent(event: BookmarksEvent) {
        when (event) {
            is BookmarksEvent.LoadBookmarks -> loadBookmarks()
        }
    }

    private fun loadBookmarks() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllBookmarks().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.value =
                        _state.value.copy(isLoading = result.isLoading)

                    is Resource.Success -> _state.value =
                        _state.value.copy(bookmarks = result.data ?: emptyList(), isLoading = false)

                    is Resource.Error -> _state.value =
                        _state.value.copy(error = result.message, isLoading = false)
                }
            }
        }
    }


}