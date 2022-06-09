package br.com.coupledev.newsapp.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.usecases.DeleteArticleUseCase
import br.com.coupledev.newsapp.domain.usecases.GetSavedArticlesUseCase
import br.com.coupledev.newsapp.domain.usecases.SaveArticleUseCase
import br.com.coupledev.newsapp.presentation.news.states.SavedArticlesState
import br.com.coupledev.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val getSavedArticlesUseCase: GetSavedArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase,
    ) : ViewModel() {

    private val _state = MutableStateFlow(SavedArticlesState())
    val state = _state.asStateFlow()

    init {
        getSavedArticles()
    }

    private fun getSavedArticles() {
        getSavedArticlesUseCase().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = SavedArticlesState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = SavedArticlesState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = SavedArticlesState(articles = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteArticle(article: Article) {
        deleteArticleUseCase(article).launchIn(viewModelScope)
    }

    fun saveArticle(article: Article) {
        saveArticleUseCase(article).launchIn(viewModelScope)
    }
}