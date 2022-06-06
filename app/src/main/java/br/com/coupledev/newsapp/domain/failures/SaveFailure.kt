package br.com.coupledev.newsapp.domain.failures

import java.lang.Exception

class SaveFailure(message: String) : Exception(message) {
}