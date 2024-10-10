package by.slizh.pexelsappcompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class PhotoEntity(
    @PrimaryKey
    val id: Int,
    val alt: String,
    @ColumnInfo(name = "avg_color") val avgColor: String,
    val height: Int,
    val width: Int,
    val liked: Boolean,
    val photographer: String,
    @ColumnInfo(name = "photographer_id") val photographerId: Int,
    @ColumnInfo(name = "photographer_url") val photographerUrl: String,
    val src: SrcEntity,
    val url: String
)