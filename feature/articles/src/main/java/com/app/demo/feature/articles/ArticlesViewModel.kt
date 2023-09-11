package com.app.demo.feature.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.demo.domain.articles.interactor.GetArticlesUseCase
import com.app.demo.domain.articles.model.ArticleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles(){
        getArticlesUseCase().flowOn(Dispatchers.IO)
            .onStart { _uiState.update { ArticlesUiState.Loading } }
            .onEach {articles -> _uiState.update { ArticlesUiState.Data(articles) }}
            .catch { error -> _uiState.update { ArticlesUiState.Error(error.message.toString()) } }
            .launchIn(viewModelScope)

    }
}
sealed interface ArticlesUiState {
    data class Data(
        val articles: List<ArticleModel>
    ) : ArticlesUiState

    data class Error(
        val errorMessage: String
    ) : ArticlesUiState

    object Loading : ArticlesUiState
}