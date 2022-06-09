package br.com.coupledev.newsapp.data.db

import androidx.room.*
import br.com.coupledev.newsapp.data.models.ArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAndInsertArticle(article: ArticleModel): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<ArticleModel>>

    @Delete
    suspend fun deleteArticle(article: ArticleModel)
}