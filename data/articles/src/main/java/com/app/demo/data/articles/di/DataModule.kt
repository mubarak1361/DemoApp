package com.app.demo.data.articles.di

import com.app.demo.data.articles.repository.ArticleRepositoryImpl
import com.app.demo.data.articles.repository.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindArticleRepository(articleRepositoryImpl: ArticleRepositoryImpl): ArticlesRepository
}