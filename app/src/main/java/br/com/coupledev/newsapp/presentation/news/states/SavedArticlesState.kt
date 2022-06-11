package br.com.coupledev.newsapp.presentation.news.states

import br.com.coupledev.newsapp.domain.entities.ArticleEntity

data class SavedArticlesState(
    val articles: List<ArticleEntity> = emptyList(),
)
