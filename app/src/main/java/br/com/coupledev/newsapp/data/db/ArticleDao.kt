package br.com.coupledev.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.coupledev.newsapp.data.models.ArticleModel

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsertArticle(article: ArticleModel): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<ArticleModel>>

    @Delete
    suspend fun deleteArticle(article: ArticleModel)
}