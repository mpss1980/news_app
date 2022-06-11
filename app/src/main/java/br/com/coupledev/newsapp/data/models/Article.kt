package br.com.coupledev.newsapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.coupledev.newsapp.domain.entities.ArticleEntity

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String? = "",
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun Article.toEntity(): ArticleEntity {
    return ArticleEntity(
        id = id,
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceEntity = source.toEntity(),
        title = title,
        url = url,
        urlToImage = urlToImage,
    );
}