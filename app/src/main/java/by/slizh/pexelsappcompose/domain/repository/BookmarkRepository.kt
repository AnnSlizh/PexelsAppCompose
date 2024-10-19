package by.slizh.pexelsappcompose.domain.repository

import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.util.Resource
import kotlinx.coroutines.flow.Flow


interface BookmarkRepository {

    suspend fun insertBookmark(photo: Photo)
    suspend fun getBookmarkById(id: Int): Flow<Resource<Photo>>
    suspend fun getAllBookmarks(): Flow<Resource<List<Photo>>>
    suspend fun deleteBookmark(photo: Photo)
}