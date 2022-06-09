package br.com.coupledev.newsapp.domain.repositories

import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.entities.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponse
    suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponse
    suspend fun updateAndInsert(article: Article): Long
    fun getSavedNews(): Flow<List<Article>>
    suspend fun deleteArticle(article: Article): Boolean
}