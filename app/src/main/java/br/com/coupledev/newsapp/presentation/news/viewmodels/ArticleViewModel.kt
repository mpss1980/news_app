package br.com.coupledev.newsapp.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.usecases.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val saveArticleUseCase: SaveArticleUseCase,
) : ViewModel() {

    fun saveArticle(articleEntity: ArticleEntity) {
        saveArticleUseCase(articleEntity).launchIn(viewModelScope)
    }
}