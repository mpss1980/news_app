package br.com.coupledev.newsapp.data.models

import br.com.coupledev.newsapp.domain.entities.SourceEntity

data class Source(
    val id: String? = null,
    val name: String
)

fun Source.toEntity(): SourceEntity {
    return SourceEntity(
        id = id,
        name = name,
    );
}