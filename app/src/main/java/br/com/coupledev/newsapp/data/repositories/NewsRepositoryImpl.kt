package br.com.coupledev.newsapp.data.repositories

import br.com.coupledev.newsapp.domain.entities.NewsResponse
import br.com.coupledev.newsapp.data.api.NewsApi
import br.com.coupledev.newsapp.data.models.toNewsResponse
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
) : NewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponse {
        try {
            val response = newsApi.getBreakNews(countryCode, pageNumber)
            return response.body()?.toNewsResponse() ?: throw GetFailure("Api response not successful")
        } catch (e: HttpException) {
            throw GetFailure(message = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            throw GetFailure(message = "Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponse {
        try {
            val response = newsApi.searchForNews(searchQuery, pageNumber)
            return response.body()?.toNewsResponse() ?: throw GetFailure("Api response not successful")
        } catch (e: HttpException) {
            throw GetFailure(message = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            throw GetFailure(message = "Couldn't reach server. Check your internet connection.")
        }
    }
}