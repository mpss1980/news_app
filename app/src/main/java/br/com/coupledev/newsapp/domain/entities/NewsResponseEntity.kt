package br.com.coupledev.newsapp.domain.entities

data class NewsResponseEntity(
    val articleEntities: List<ArticleEntity>,
    val status: String,
    val totalResults: Int
)
