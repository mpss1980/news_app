package br.com.coupledev.newsapp.domain.failures

import java.lang.Exception

class GetFailure(message: String) : Exception(message) {
}