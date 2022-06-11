package br.com.coupledev.newsapp.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.usecases.DeleteArticleUseCase
import br.com.coupledev.newsapp.domain.usecases.GetSavedArticlesUseCase
import br.com.coupledev.newsapp.domain.usecases.SaveArticleUseCase
import br.com.coupledev.newsapp.presentation.news.events.ArticleEvent
import br.com.coupledev.newsapp.presentation.news.states.SavedArticlesState
import br.com.coupledev.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var getSavedNewsJob: Job? = null

    init {
        getSavedArticles()
    }

    fun onEvent(event: ArticleEvent) {
        when (event) {
            is ArticleEvent.DeleteArticle -> {
                deleteArticleUseCase(event.article).launchIn(viewModelScope)
            }
            is ArticleEvent.SaveArticle -> {
                saveArticleUseCase(event.article).launchIn(viewModelScope)
            }
        }
    }

    private fun getSavedArticles() {
        getSavedNewsJob?.cancel()
        getSavedNewsJob = getSavedArticlesUseCase().onEach { articles ->
            _state.value = state.value.copy(
                articles = articles,
            )
        }.launchIn(viewModelScope)
    }
}