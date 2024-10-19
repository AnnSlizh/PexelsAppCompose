package by.slizh.pexelsappcompose.data.repository

import by.slizh.pexelsappcompose.data.mappers.toFeaturedCollection
import by.slizh.pexelsappcompose.data.mappers.toPhoto
import by.slizh.pexelsappcompose.data.remote.PexelsAppApi
import by.slizh.pexelsappcompose.domain.model.FeaturedCollection
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.repository.PhotoRepository
import by.slizh.pexelsappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val pexelsAppApi: PexelsAppApi) :
    PhotoRepository {
    override suspend fun getSearchPhotoList(query: String): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))

            val searchPhotosFromApi = try {
                pexelsAppApi.getSearchPhotoList(query)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading search photos"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading search photos"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading search photos"))
                return@flow
            }

            emit(
                Resource.Success(
                    searchPhotosFromApi.photos.let { it.map { photoDto -> photoDto.toPhoto() } })
            )
            emit(Resource.Loading(false))

        }
    }

    override suspend fun getCuratedPhotoList(): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))

            val curatedPhotoFromApi = try {
                pexelsAppApi.getCuratedPhotoList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading curated photos"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading curated photos"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading curated photos"))
                return@flow
            }

            emit(
                Resource.Success(
                    curatedPhotoFromApi.photos.let { it.map { photoDto -> photoDto.toPhoto() } })
            )
            emit(Resource.Loading(false))

        }
    }

    override suspend fun getPhotoById(photoId: Int): Flow<Resource<Photo>> {
        return flow {
            emit(Resource.Loading(true))

            val photoByIdFromApi = try {
                pexelsAppApi.getPhotoById(photoId)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photo by ID"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photo by ID"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading photo by ID"))
                return@flow
            }

            emit(
                Resource.Success(
                    photoByIdFromApi.toPhoto()
                )
            )
            emit(Resource.Loading(false))

        }

    }

    override suspend fun getFeaturedCollections(): Flow<Resource<FeaturedCollection>> {
        return flow {
            emit(Resource.Loading(true))

            val featuredCollectionFromApi = try {
                pexelsAppApi.getFeaturedCollections()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading featured collection"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading featured collection"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading featured collection"))
                return@flow
            }
            emit(
                Resource.Success(
                    featuredCollectionFromApi.toFeaturedCollection()
                )
            )
            emit(Resource.Loading(false))
        }
    }
}