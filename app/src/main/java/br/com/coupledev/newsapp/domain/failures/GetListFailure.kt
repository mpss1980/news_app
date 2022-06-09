package br.com.coupledev.newsapp.domain.failures

import java.lang.Exception

class GetListFailure(message: String) : Exception(message) {
}