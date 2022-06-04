package br.com.coupledev.newsapp.domain.entities

data class Article(
    var id: Int? = null,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val source: Source,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
)