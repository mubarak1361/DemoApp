package com.app.demo.domain.articles.interactor

import com.app.demo.domain.articles.mapper.transformArticleModel
import com.app.demo.data.articles.repository.ArticlesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository, private val formatArticlePublishedDateUseCase: FormatArticlePublishedDateUseCase) {

    operator fun invoke() = flow {
        val articles = articlesRepository.getArticles()
        val articleModels = articles.map {
            val formattedArticlePublishedDate = formatArticlePublishedDateUseCase(date = it.publishedDate)
            it.transformArticleModel(formattedArticlePublishedDate) }
        emit(articleModels)
    }
}