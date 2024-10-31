package by.slizh.pexelsappcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.slizh.pexelsappcompose.data.local.entity.PhotoEntity

@Dao
interface BookmarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(photo: PhotoEntity)

    @Query("SELECT * FROM bookmarks WHERE id=:id")
    fun getBookmarkById(id: Int): PhotoEntity?

    @Query("SELECT * FROM bookmarks ")
    fun getAllBookmarks(): List<PhotoEntity>

    @Delete
    suspend fun deleteBookmark(photo: PhotoEntity)
}