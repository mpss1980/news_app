package br.com.coupledev.newsapp.data.models

import br.com.coupledev.newsapp.domain.entities.Source

data class SourceModel(
    val id: String? = null,
    val name: String
)

fun SourceModel.toSource(): Source {
    return Source(
        id = id,
        name = name,
    );
}