package com.app.demo.domain.articles.di

import com.app.demo.domain.articles.interactor.GetArticleDetailUseCase
import com.app.demo.domain.articles.interactor.GetArticlesUseCase
import com.app.demo.data.articles.repository.ArticlesRepository
import com.app.demo.domain.articles.interactor.FormatArticlePublishedDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleDomainModule {

    @Provides
    fun provideGetArticleDetailUseCase(articlesRepository: ArticlesRepository,formatArticlePublishedDateUseCase: FormatArticlePublishedDateUseCase): GetArticleDetailUseCase =
        GetArticleDetailUseCase(articlesRepository,formatArticlePublishedDateUseCase)

    @Provides
    fun provideGetArticlesUseCase(articlesRepository: ArticlesRepository,formatArticlePublishedDateUseCase: FormatArticlePublishedDateUseCase): GetArticlesUseCase =
        GetArticlesUseCase(articlesRepository,formatArticlePublishedDateUseCase)

    @Singleton
    @Provides
    fun provideFormatArticlePublishedDateUseCase() = FormatArticlePublishedDateUseCase()
}