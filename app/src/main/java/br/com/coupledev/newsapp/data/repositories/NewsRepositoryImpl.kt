package br.com.coupledev.newsapp.data.repositories

import br.com.coupledev.newsapp.domain.entities.NewsResponseEntity
import br.com.coupledev.newsapp.data.api.NewsApi
import br.com.coupledev.newsapp.data.db.ArticleDao
import br.com.coupledev.newsapp.data.models.*
import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.failures.DeleteFailure
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.failures.GetListFailure
import br.com.coupledev.newsapp.domain.failures.SaveFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val db: ArticleDao,
) : NewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): NewsResponseEntity {
        try {
            val response = newsApi.getBreakNews(countryCode, pageNumber)
            return response.body()?.toEntity() ?: throw GetFailure("Api response not successful")
        } catch (e: HttpException) {
            throw GetFailure(message = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            throw GetFailure(message = "Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponseEntity {
        try {
            val response = newsApi.searchForNews(searchQuery, pageNumber)
            return response.body()?.toEntity() ?: throw GetFailure("Api response not successful")
        } catch (e: HttpException) {
            throw GetFailure(message = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            throw GetFailure(message = "Couldn't reach server. Check your internet connection.")
        }
    }

    override suspend fun updateAndInsert(articleEntity: ArticleEntity): Long {
        try {
            return db.updateAndInsertArticle(createArticleFromArticleEntity(articleEntity))
        } catch (e: Exception) {
            throw SaveFailure(message = e.localizedMessage ?: "Couldn't save locally.")
        }
    }

    override fun getSavedNews(): Flow<List<ArticleEntity>> {
        try {
            return db.getAllArticles().map { articles ->
                articles.map { article -> article.toEntity() }
            }
        } catch (e: Exception) {
            throw GetListFailure(message = e.localizedMessage ?: "Couldn't get saved news.")
        }
    }

    override suspend fun deleteArticle(articleEntity: ArticleEntity): Boolean {
        try {
            db.deleteArticle(createArticleFromArticleEntity(articleEntity))
            return true
        } catch (e: Exception) {
            throw DeleteFailure(message = e.localizedMessage ?: "Couldn't delete this news.")
        }
    }

    private fun createArticleFromArticleEntity(articleEntity: ArticleEntity): Article {
        val sourceModel = Source(id = articleEntity.sourceEntity.id, name = articleEntity.sourceEntity.name)
        return Article(
            id = articleEntity.id,
            author = articleEntity.author,
            content = articleEntity.content,
            description = articleEntity.description,
            publishedAt = articleEntity.publishedAt,
            source = sourceModel,
            title = articleEntity.title,
            url = articleEntity.url,
            urlToImage = articleEntity.urlToImage,
        )
    }
}