package by.slizh.pexelsappcompose.presentation.viewModels

sealed class PhotoEvent {
    data class SearchPhotos(val query: String) : PhotoEvent()
  //  data class GetPhotoById(val photoId: Int) : PhotoEvent()
    data class SelectFeatureCollection(val query: String) : PhotoEvent()
    object LoadCuratedPhotos : PhotoEvent()
    object LoadFeaturedCollections : PhotoEvent()
}