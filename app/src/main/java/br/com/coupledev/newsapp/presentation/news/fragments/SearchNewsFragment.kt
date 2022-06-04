package br.com.coupledev.newsapp.presentation.news.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.databinding.FragmentSearchNewsBinding

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    private lateinit var binding: FragmentSearchNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchNewsBinding.bind(view)
    }

}