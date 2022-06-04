package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.NewsResponse
import br.com.coupledev.newsapp.domain.failures.GetFailure
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import br.com.coupledev.newsapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(countryCode: String, pageNumber: Int): Flow<Resource<NewsResponse>> = flow {
        if (pageNumber < 0) emit(Resource.Error("pageNumber should be greater than 0"))
        try {
            emit(Resource.Loading())
            val news = repository.getBreakingNews(countryCode, pageNumber)
            emit(Resource.Success(news))
        } catch (e: GetFailure) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}