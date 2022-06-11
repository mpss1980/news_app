package br.com.coupledev.newsapp.domain.usecases

import br.com.coupledev.newsapp.domain.entities.ArticleEntity
import br.com.coupledev.newsapp.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(): Flow<List<ArticleEntity>> {
        return repository.getSavedNews()
    }
}