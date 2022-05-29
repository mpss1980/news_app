package br.com.coupledev.newsapp.data.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)