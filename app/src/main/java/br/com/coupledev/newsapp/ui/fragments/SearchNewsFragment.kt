package br.com.coupledev.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.databinding.FragmentSavedNewsBinding
import br.com.coupledev.newsapp.databinding.FragmentSearchNewsBinding
import br.com.coupledev.newsapp.ui.NewsActivity
import br.com.coupledev.newsapp.ui.NewsViewModel

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private lateinit var binding: FragmentSearchNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
    }

}