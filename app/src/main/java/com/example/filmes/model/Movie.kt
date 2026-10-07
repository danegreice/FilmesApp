package com.example.filmes.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val titulo: String,
    val ano: Int,
    val genero: String,
    val imagem: String,
    val resumo: String
)