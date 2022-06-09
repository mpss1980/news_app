package br.com.coupledev.newsapp.presentation.news.states

import br.com.coupledev.newsapp.domain.entities.Article

data class SavedArticlesState(
    val isLoading: Boolean = false,
    val articles: List<Article>? = null,
    val error: String = ""
)
