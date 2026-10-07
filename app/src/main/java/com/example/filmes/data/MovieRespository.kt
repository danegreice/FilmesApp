package com.example.filmes.data

import android.content.Context
import com.example.filmes.model.Movie
import kotlinx.serialization.json.Json

class MovieRespository(
    private val context: Context
) {
    fun getMovies(): List<Movie> {
        val jsonString = context.assets
            .open("filmes.json")
            .bufferedReader()
            .use { it.readText()}

        return Json {
            ignoreUnknownKeys = true
        }.decodeFromString(jsonString)
    }
}