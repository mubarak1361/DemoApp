package com.app.demo.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.demo.core.local.model.ArticleEntity

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE id=:id")
    suspend fun findById(id: Int): ArticleEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articleEntity: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: ArticleEntity)

    @Delete
    suspend fun delete(articleEntity: ArticleEntity)

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}