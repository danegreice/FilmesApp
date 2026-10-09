package com.example.filmes.data

import android.content.Context
import com.example.filmes.model.Movie
import com.example.filmes.model.MovieEntity
import kotlinx.serialization.json.Json

class MovieRespository(
    private val context: Context,
    private val movieDao: MovieDao
) {
    fun getMoviesFromJson(): List<Movie> {
        val json = context.assets
            .open("filmes.json")
            .bufferedReader()
            .use { it.readText() }

        return Json.decodeFromString(json)
    }

    suspend fun saveMoviesFromJson() {
        val moviesFromJson = getMoviesFromJson()

        val entities = moviesFromJson.map { movie ->
            MovieEntity(
                id = movie.id,
                titulo = movie.titulo,
                ano = movie.ano,
                genero = movie.genero,
                imagem = movie.imagem,
                resumo = movie.resumo
            )
        }

        movieDao.insertMovies(entities)
    }

    suspend fun getMovies(): List<Movie> {
        return movieDao.getAllMovies().map { entity ->
            Movie (
                id = entity.id,
                titulo = entity.titulo,
                ano = entity.ano,
                genero = entity.genero,
                imagem = entity.imagem,
                resumo = entity.resumo
            )
        }
    }

}