package br.com.coupledev.newsapp.data.models

import br.com.coupledev.newsapp.domain.entities.NewsResponseEntity

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

fun NewsResponse.toEntity(): NewsResponseEntity {
    return NewsResponseEntity(
        articleEntities = articles.map { article -> article.toEntity() },
        status = status,
        totalResults = totalResults,
    );
}
