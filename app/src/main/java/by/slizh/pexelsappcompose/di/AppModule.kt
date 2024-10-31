package by.slizh.pexelsappcompose.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import by.slizh.pexelsappcompose.data.local.BookmarksDatabase
import by.slizh.pexelsappcompose.data.remote.PexelsAppApi
import by.slizh.pexelsappcompose.util.photoDownloader.Downloader
import by.slizh.pexelsappcompose.util.photoDownloader.ImageDownloader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providePexelsApi(): PexelsAppApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PexelsAppApi.BASE_URl)
            .client(client)
            .build()
            .create(PexelsAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookmarksDB(app: Application): BookmarksDatabase {
        return Room.databaseBuilder(
            app,
            BookmarksDatabase::class.java,
            "bookmarks.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDownloader(@ApplicationContext context: Context): Downloader {
        return ImageDownloader(context)
    }

}