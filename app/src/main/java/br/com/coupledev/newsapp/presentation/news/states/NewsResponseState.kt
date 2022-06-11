package br.com.coupledev.newsapp.presentation.news.states

import br.com.coupledev.newsapp.domain.entities.NewsResponseEntity

data class NewsResponseState(
    val isLoading: Boolean = false,
    val news: NewsResponseEntity? = null,
    val error: String = ""
)
