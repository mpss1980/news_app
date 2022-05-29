package br.com.coupledev.newsapp.repositories

import br.com.coupledev.newsapp.api.RetrofitInstance
import br.com.coupledev.newsapp.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase,
) {

    suspend fun getBrakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakNews(countryCode, pageNumber)
}