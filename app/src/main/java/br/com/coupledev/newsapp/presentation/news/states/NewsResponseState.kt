package br.com.coupledev.newsapp.presentation.news.states

import br.com.coupledev.newsapp.domain.entities.NewsResponse

data class NewsResponseState(
    val isLoading: Boolean = false,
    val news: NewsResponse? = null,
    val error: String = ""
)
