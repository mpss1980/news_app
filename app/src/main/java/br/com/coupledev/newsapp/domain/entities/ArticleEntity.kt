package br.com.coupledev.newsapp.domain.entities

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class ArticleEntity(
    var id: Int? = null,
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val publishedAt: String? = "",
    val sourceEntity: SourceEntity,
    val title: String? = "",
    val url: String? = "",
    val urlToImage: String? = ""
): Serializable