package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.failures.SaveFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(articleEntity: ArticleEntity): Flow<Resource<Long>> = flow {
        try {
            emit(Resource.Loading())
            val id = repository.updateAndInsert(articleEntity)
            emit(Resource.Success(id))
        } catch (e: SaveFailure) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}