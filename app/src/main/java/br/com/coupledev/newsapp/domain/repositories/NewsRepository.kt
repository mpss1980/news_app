package br.com.coupledev.newsapp.domain.repositories

import androidx.lifecycle.LiveData
import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.entities.NewsResponse

interface NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponse
    suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponse
    suspend fun updateAndInsert(article: Article): Long
    fun getSavedNews(): LiveData<List<Article>>
    suspend fun deleteArticle(article: Article): Boolean
}