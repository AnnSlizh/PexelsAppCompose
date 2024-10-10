package by.slizh.pexelsappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.slizh.pexelsappcompose.data.local.dao.BookmarkDao
import by.slizh.pexelsappcompose.data.local.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
@TypeConverters(SrcEntityConverter::class)
abstract class BookmarksDatabase : RoomDatabase() {

    abstract fun bookmarkDao(): BookmarkDao

}