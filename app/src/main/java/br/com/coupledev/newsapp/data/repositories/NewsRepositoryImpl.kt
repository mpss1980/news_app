package br.com.coupledev.newsapp.data.repositories

import androidx.lifecycle.LiveData
import br.com.coupledev.newsapp.domain.entities.NewsResponse
import br.com.coupledev.newsapp.data.api.NewsApi
import br.com.coupledev.newsapp.data.db.ArticleDao
import br.com.coupledev.newsapp.data.models.*
import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.failures.SaveFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val db: ArticleDao,
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

    override suspend fun updateAndInsert(article: Article): Long {
        try {
            val sourceModel = SourceModel(id = article.source.id, name = article.source.name)
            val model = ArticleModel(
                id = article.id,
                author = article.author,
                content = article.content,
                description = article.description,
                publishedAt = article.publishedAt,
                source = sourceModel,
                title = article.title,
                url = article.url,
                urlToImage = article.urlToImage,
            )
            return db.updateAndInsertArticle(model)
        } catch (e: HttpException) { //todo: continue here, take the correct exception and catch here
            throw SaveFailure(message = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) { //todo: continue here, take the correct exception and catch here
            throw SaveFailure(message = "Couldn't save locally.")
        }
    }

    override fun getSavedNews(): LiveData<List<Article>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(article: Article): Boolean {
        TODO("Not yet implemented")
    }
}