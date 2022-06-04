package br.com.coupledev.newsapp.presentation.news.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
    }
}