package br.com.coupledev.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.coupledev.newsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsertArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}