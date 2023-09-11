package com.app.demo.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.demo.domain.articles.interactor.GetArticleDetailUseCase
import com.app.demo.domain.articles.model.ArticleDetailsModel
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
class ArticleDetailViewModel @Inject constructor(private val getArticleDetailUseCase: GetArticleDetailUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow<ArticleDetailsUiState>(ArticleDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getArticleDetailsById(id: Int) {
        getArticleDetailUseCase(id)
            .flowOn(Dispatchers.IO)
            .onStart { _uiState.update { ArticleDetailsUiState.Loading } }
            .onEach { articleDetails -> _uiState.update { ArticleDetailsUiState.Data(articleDetails) } }
            .catch { error -> _uiState.update { ArticleDetailsUiState.Error(error.message.toString()) } }
            .launchIn(viewModelScope)

    }
}

sealed interface ArticleDetailsUiState {
    data class Data(
        val articleDetails: ArticleDetailsModel
    ) : ArticleDetailsUiState

    data class Error(
        val errorMessage: String
    ) : ArticleDetailsUiState

    object Loading : ArticleDetailsUiState
}