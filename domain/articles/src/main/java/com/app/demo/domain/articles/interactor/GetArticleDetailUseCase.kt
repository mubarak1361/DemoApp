package com.app.demo.domain.articles.interactor

import com.app.demo.domain.articles.mapper.transformArticleDetailsModel
import com.app.demo.data.articles.repository.ArticlesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticleDetailUseCase @Inject constructor(private val articlesRepository: ArticlesRepository, private val formatArticlePublishedDateUseCase: FormatArticlePublishedDateUseCase) {

    operator fun invoke(articleId: Int) = flow{
        val articleDetails = articlesRepository.getArticleDetails(id = articleId)
        val formattedArticlePublishedDate = formatArticlePublishedDateUseCase(date = articleDetails.publishedDate)
        emit(articleDetails.transformArticleDetailsModel(formattedArticlePublishedDate))
    }
}