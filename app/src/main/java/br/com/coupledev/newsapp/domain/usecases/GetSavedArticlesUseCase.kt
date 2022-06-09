package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.Article
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            repository.getSavedNews().map { savedArticles ->
                emit(Resource.Success(savedArticles))
            }
        } catch (e: GetFailure) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}