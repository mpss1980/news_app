package br.com.coupledev.newsapp.data.repositories

import br.com.coupledev.newsapp.domain.entities.NewsResponse
import br.com.coupledev.newsapp.data.api.NewsApi
import br.com.coupledev.newsapp.data.db.ArticleDao
import br.com.coupledev.newsapp.data.models.*
import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.failures.DeleteFailure
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.failures.GetListFailure
import br.com.coupledev.newsapp.domain.failures.SaveFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            return db.updateAndInsertArticle(createArticleModelFromArticle(article))
        } catch (e: Exception) {
            throw SaveFailure(message = e.localizedMessage ?: "Couldn't save locally.")
        }
    }

    override fun getSavedNews(): Flow<List<Article>> {
        try {
            return db.getAllArticles().map { models ->
                models.map { articleModel -> articleModel.toArticle() }
            } //todo: debug here - isn't loading article list
        } catch (e: Exception) {
            throw GetListFailure(message = e.localizedMessage ?: "Couldn't get saved news.")
        }
    }

    override suspend fun deleteArticle(article: Article): Boolean {
        try {
            db.deleteArticle(createArticleModelFromArticle(article))
            return true
        } catch (e: Exception) {
            throw DeleteFailure(message = e.localizedMessage ?: "Couldn't delete this news.")
        }
    }

    private fun createArticleModelFromArticle(article: Article): ArticleModel {
        val sourceModel = SourceModel(id = article.source.id, name = article.source.name)
        return ArticleModel(
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
    }
}