package br.com.coupledev.newsapp.presentation.news.events

import br.com.coupledev.newsapp.domain.entities.ArticleEntity

sealed class ArticleEvent {
    data class DeleteArticle(val article: ArticleEntity): ArticleEvent()
    data class SaveArticle(val article: ArticleEntity): ArticleEvent()
}