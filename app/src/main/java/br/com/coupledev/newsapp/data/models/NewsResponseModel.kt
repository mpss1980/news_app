package br.com.coupledev.newsapp.data.models

import br.com.coupledev.newsapp.domain.entities.NewsResponse

data class NewsResponseModel(
    val articles: List<ArticleModel>,
    val status: String,
    val totalResults: Int
)

fun NewsResponseModel.toNewsResponse(): NewsResponse {
    return NewsResponse(
        articles = articles.map { articleModel -> articleModel.toArticle() },
        status = status,
        totalResults = totalResults,
    );
}
