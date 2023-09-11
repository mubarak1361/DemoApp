package com.app.demo.core.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val description: String,
    val type: String,
    val image: String,
    @ColumnInfo("created_date")
    val createdDate: String,
    @ColumnInfo("updated_date")
    val updatedDate: String,
    val byline: String,
    val region: String,
    @ColumnInfo("published_date")
    val publishedDate: String,
    @ColumnInfo("image_caption")
    val imageCaption: String,
    @ColumnInfo("image_copyright")
    val imageCopyright: String,
    @ColumnInfo("read_more_link")
    val readMoreLink: String
)
