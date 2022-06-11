package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.NewsResponseEntity
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(searchQuery: String, pageNumber: Int): Flow<Resource<NewsResponseEntity>> = flow {
        if (pageNumber < 0) emit(Resource.Error("pageNumber should be greater than 0"))
        try {
            emit(Resource.Loading())
            val news = repository.searchForNews(searchQuery, pageNumber)
            emit(Resource.Success(news))
        } catch (e: GetFailure) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}