package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.failures.SaveFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(articleEntity: ArticleEntity): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading())
            val articleDeleted = repository.deleteArticle(articleEntity)
            emit(Resource.Success(articleDeleted))
        } catch (e: SaveFailure) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}