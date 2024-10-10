package by.slizh.pexelsappcompose.data.remote

import by.slizh.pexelsappcompose.data.remote.dto.FeaturedCollectionDto
import by.slizh.pexelsappcompose.data.remote.dto.PhotoDto
import by.slizh.pexelsappcompose.data.remote.dto.PhotoListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelsAppApi {

    @GET("search")
    suspend fun getSearchPhotoList(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = PER_PAGE,
        @Query("page") page: Int = PAGE,
        @Header("Authorization") authorization: String = API_KEY
    ): PhotoListDto

    @GET("curated")
    suspend fun getCuratedPhotoList(
        @Query("per_page") perPage: Int = PER_PAGE,
        @Query("page") page: Int = PAGE,
        @Header("Authorization") authorization: String = API_KEY
    ): PhotoListDto

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String = API_KEY
    ): PhotoDto

    @GET("collections/featured")
    suspend fun getFeaturedCollections(
        @Query("per_page") perPage: Int = PER_PAGE,
        @Header("Authorization") authorization: String = API_KEY
    ): FeaturedCollectionDto

    companion object {
        const val BASE_URl = "https://api.pexels.com/v1/"
        const val API_KEY = "pVwrhqiHKR1n3Q4lsgbmL7PSU2hYOLDHAJKtFB5jjInJHUn0acqRxyW8"
        const val PER_PAGE = 30
        const val FEATURED_PER_PAGE = 7
        const val PAGE = 1
    }
}