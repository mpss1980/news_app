package br.com.coupledev.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.coupledev.newsapp.domain.entities.Article

@Entity(
    tableName = "articles"
)
data class ArticleModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = "",
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceModel,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun ArticleModel.toArticle(): Article {
    return Article(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toSource(),
        title = title,
        url = url,
        urlToImage = urlToImage,
    );
}