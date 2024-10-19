package by.slizh.pexelsappcompose.data.mappers

import by.slizh.pexelsappcompose.data.local.entity.PhotoEntity
import by.slizh.pexelsappcompose.data.local.entity.SrcEntity
import by.slizh.pexelsappcompose.data.remote.dto.Collection
import by.slizh.pexelsappcompose.data.remote.dto.FeaturedCollectionDto
import by.slizh.pexelsappcompose.data.remote.dto.PhotoDto
import by.slizh.pexelsappcompose.data.remote.dto.Src

import by.slizh.pexelsappcompose.domain.model.CollectionModel
import by.slizh.pexelsappcompose.domain.model.FeaturedCollection
import by.slizh.pexelsappcompose.domain.model.Photo
import by.slizh.pexelsappcompose.domain.model.SrcModel
import com.google.gson.annotations.SerializedName

fun PhotoDto.toPhoto(): Photo {
    return Photo(
        id = id,
        alt = alt,
        avgColor = avgColor,
        height = height,
        width = width,
        liked = liked,
        photographer = photographer,
        photographerId = photographerId,
        photographerUrl = photographerUrl,
        src = src.toSrcModel(),
        url = url
    )
}

fun Src.toSrcModel(): SrcModel {
    return SrcModel(
        original = original,
        large = large,
        large2x = large2x,
        medium = medium,
        small = small,
        portrait = portrait,
        landscape = landscape,
        tiny = tiny
    )
}

fun FeaturedCollectionDto.toFeaturedCollection(): FeaturedCollection {
    return FeaturedCollection(
        collections = collections.map { it.toCollectionModel() },
        page = page,
        perPage = perPage,
        totalResults = totalResults
    )
}

fun Collection.toCollectionModel(): CollectionModel {
    return CollectionModel(
        id = id,
        title = title,
        description = description,
        private = private,
        mediaCount = mediaCount,
        photosCount = photosCount,
        videosCount = videosCount
    )
}

fun Photo.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        alt = alt,
        avgColor = avgColor,
        height = height,
        width = width,
        liked = liked,
        photographer = photographer,
        photographerId = photographerId,
        photographerUrl = photographerUrl,
        src = src.toSrcEntity(),
        url = url
    )
}

fun SrcModel.toSrcEntity(): SrcEntity {
    return SrcEntity(
        original = original,
        large = large,
        large2x = large2x,
        medium = medium,
        small = small,
        portrait = portrait,
        landscape = landscape,
        tiny = tiny
    )
}


fun PhotoEntity.toPhoto(): Photo {
    return Photo(
        id = id,
        alt = alt,
        avgColor = avgColor,
        height = height,
        width = width,
        liked = liked,
        photographer = photographer,
        photographerId = photographerId,
        photographerUrl = photographerUrl,
        src = src.toSrcModel(),
        url = url
    )
}

fun SrcEntity.toSrcModel(): SrcModel {
    return SrcModel(
        original = original,
        large = large,
        large2x = large2x,
        medium = medium,
        small = small,
        portrait = portrait,
        landscape = landscape,
        tiny = tiny
    )
}

