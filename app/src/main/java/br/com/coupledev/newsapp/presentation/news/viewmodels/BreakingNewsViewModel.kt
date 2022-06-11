package br.com.coupledev.newsapp.presentation.news.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.coupledev.newsapp.domain.usecases.GetBreakingNewsUseCase
import br.com.coupledev.newsapp.presentation.news.states.NewsResponseState
import br.com.coupledev.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val getBreakingNewsUseCase: GetBreakingNewsUseCase
): ViewModel() {

    private val _state = MutableStateFlow(NewsResponseState())
    val state = _state.asStateFlow()

    var breakingNewsPage = 1

    init {
        getBreakingNews()
    }

    private fun getBreakingNews() {
        getBreakingNewsUseCase("br", breakingNewsPage).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = NewsResponseState(error = result.message?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = NewsResponseState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = NewsResponseState(news = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}