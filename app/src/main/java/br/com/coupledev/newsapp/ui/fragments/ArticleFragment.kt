package br.com.coupledev.newsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.databinding.FragmentArticleBinding
import br.com.coupledev.newsapp.databinding.FragmentSearchNewsBinding
import br.com.coupledev.newsapp.ui.NewsActivity
import br.com.coupledev.newsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding
    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
    }
}