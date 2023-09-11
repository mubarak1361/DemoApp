package com.app.demo.core.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.demo.core.local.dao.ArticleDao
import com.app.demo.core.local.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class DemoDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}