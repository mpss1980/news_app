package br.com.coupledev.newsapp.presentation.news.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.coupledev.newsapp.R
import br.com.coupledev.newsapp.databinding.FragmentSavedNewsBinding

class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var binding: FragmentSavedNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
    }

}