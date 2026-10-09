package com.example.filmes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmes")
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val ano: Int,
    val genero: String,
    val imagem: String,
    val resumo: String
)