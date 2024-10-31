package by.slizh.pexelsappcompose.data.repository

import by.slizh.pexelsappcompose.data.local.BookmarksDatabase
import by.slizh.pexelsappcompose.data.mappers.toPhoto
import by.slizh.pexelsappcompose.data.mappers.toPhotoEntity
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.repository.BookmarkRepository
import by.slizh.pexelsappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(private val bookmarksDatabase: BookmarksDatabase) :
    BookmarkRepository {
    override suspend fun insertBookmark(photo: Photo) {
        bookmarksDatabase.bookmarkDao().insertBookmark(photo.toPhotoEntity())
    }

    override suspend fun getBookmarkById(id: Int): Flow<Resource<Photo>> {
        return flow {
            emit(Resource.Loading(true))

            val photoEntity = bookmarksDatabase.bookmarkDao().getBookmarkById(id)
            if (photoEntity != null) {
                emit(
                    Resource.Success(photoEntity.toPhoto())
                )
            }
            emit(Resource.Loading(false))
            return@flow
        }
    }

    override suspend fun getAllBookmarks(): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))

            val bookmarksList = bookmarksDatabase.bookmarkDao().getAllBookmarks()
            if (bookmarksList.isNotEmpty()) {
                emit(Resource.Success(data = bookmarksList.map { photoEntity -> photoEntity.toPhoto() }))
                emit(Resource.Loading(false))
                return@flow
            } else {
                emit(Resource.Error(message = "You haven't saved anything yet"))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }

    override suspend fun deleteBookmark(photo: Photo) {
        bookmarksDatabase.bookmarkDao().deleteBookmark(photo.toPhotoEntity())
    }
}