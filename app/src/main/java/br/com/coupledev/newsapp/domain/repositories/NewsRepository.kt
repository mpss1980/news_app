package br.com.coupledev.newsapp.domain.repositories

import br.com.coupledev.newsapp.domain.entities.NewsResponse

interface NewsRepository {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponse
    suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponse
}