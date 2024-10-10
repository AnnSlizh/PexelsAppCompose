package by.slizh.pexelsappcompose.data.local.repository

import by.slizh.pexelsappcompose.data.remote.PexelsAppApi
import by.slizh.pexelsappcompose.domain.model.FeaturedCollection
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.repository.PhotoRepository
import by.slizh.pexelsappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val pexelsAppApi: PexelsAppApi) :
    PhotoRepository {
    override suspend fun getSearchPhotoList(query: String): Flow<Resource<List<Photo>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCuratedPhotoList(): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))
        }
    }

    override suspend fun getPhotoById(photoId: Int): Flow<Resource<Photo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFeaturedCollections(): Flow<Resource<List<FeaturedCollection>>> {
        TODO("Not yet implemented")
    }
}