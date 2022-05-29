package br.com.coupledev.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.coupledev.newsapp.data.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsertArticle(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}