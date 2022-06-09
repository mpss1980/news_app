package br.com.coupledev.newsapp.domain.failures

import java.lang.Exception

class DeleteFailure(message: String) : Exception(message) {
}