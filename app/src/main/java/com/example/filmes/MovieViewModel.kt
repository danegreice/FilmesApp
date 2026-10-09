package com.example.filmes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmes.data.MovieRespository
import com.example.filmes.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRespository
) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())

    val movies: StateFlow<List<Movie>> = _movies

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            repository.saveMoviesFromJson()

            _movies.value = repository.getMovies()
        }
    }
}