package br.com.coupledev.newsapp.domain.repositories

import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.entities.NewsResponseEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponseEntity
    suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponseEntity
    suspend fun updateAndInsert(articleEntity: ArticleEntity): Long
    fun getSavedNews(): Flow<List<ArticleEntity>>
    suspend fun deleteArticle(articleEntity: ArticleEntity): Boolean
}