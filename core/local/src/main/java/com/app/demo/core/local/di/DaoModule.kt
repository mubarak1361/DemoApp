package com.app.demo.core.local.di

import com.app.demo.core.local.dao.ArticleDao
import com.app.demo.core.local.db.DemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesTopicsDao(
        database: DemoDatabase,
    ): ArticleDao = database.articleDao()

}