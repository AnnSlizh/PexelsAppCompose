package by.slizh.pexelsappcompose.presentation.viewModels.homePhotos

sealed class PhotoEvent {
    data class SearchPhotos(val query: String) : PhotoEvent()
    data class SelectFeatureCollection(val query: String) : PhotoEvent()
    object LoadCuratedPhotos : PhotoEvent()
    object LoadFeaturedCollections : PhotoEvent()
}