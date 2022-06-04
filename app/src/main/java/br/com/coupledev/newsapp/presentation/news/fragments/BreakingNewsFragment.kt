package br.com.coupledev.newsapp.presentation.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.presentation.news.adapters.NewsAdapter
import br.com.coupledev.newsapp.databinding.FragmentBreakingNewsBinding
import br.com.coupledev.newsapp.presentation.news.states.NewsResponseState
import br.com.coupledev.newsapp.presentation.news.viewmodels.BreakingNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    private val TAG = "BreakingNewsFragment"

    private val viewModel: BreakingNewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)
        setupRecyclerView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest { state ->
                    if (state.error.isNotEmpty()) {
                        hideProgressBar()
                        state.error.let { message ->
                            Log.e(TAG, "An error occurred: $message")
                        }
                    }
                    if (state.isLoading) {
                        showProgressBar()
                    }
                    if (state.news != null) {
                        hideProgressBar()
                        state.news.let { news ->
                            newsAdapter.differ.submitList(news.articles)
                        }
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}