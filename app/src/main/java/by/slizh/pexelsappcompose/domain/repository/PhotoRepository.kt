package by.slizh.pexelsappcompose.domain.repository

import by.slizh.pexelsappcompose.domain.model.FeaturedCollection
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun getSearchPhotoList(query: String): Flow<Resource<List<Photo>>>

    suspend fun getCuratedPhotoList(): Flow<Resource<List<Photo>>>

    suspend fun getPhotoById(photoId: Int): Flow<Resource<Photo>>

    suspend fun getFeaturedCollections(): Flow<Resource<List<FeaturedCollection>>>
}