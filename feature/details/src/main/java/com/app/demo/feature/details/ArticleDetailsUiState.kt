package com.app.demo.feature.details

import com.app.demo.domain.articles.model.ArticleDetailsModel

sealed interface ArticleDetailsUiState {
    data class Data(
        val articleDetails: ArticleDetailsModel
    ) : ArticleDetailsUiState

    data class Error(
        val errorMessage: String
    ) : ArticleDetailsUiState

    object Loading : ArticleDetailsUiState
}