package by.slizh.pexelsappcompose.di

import by.slizh.pexelsappcompose.data.repository.BookmarkRepositoryImpl
import by.slizh.pexelsappcompose.data.repository.PhotoRepositoryImpl
import by.slizh.pexelsappcompose.domain.repository.BookmarkRepository
import by.slizh.pexelsappcompose.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotoRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPhotoRepository(
        photoRepositoryImpl: PhotoRepositoryImpl
    ): PhotoRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BookmarkRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository
}