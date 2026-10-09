package com.example.filmes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.filmes.data.MovieDatabase
import com.example.filmes.data.MovieRespository
import com.example.filmes.ui.theme.FilmesTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MovieDatabase.getDatabase(applicationContext)
        val movieDao = database.movieDao()
        val repository = MovieRespository(
            applicationContext,
            movieDao
        )
        val viewModel = MovieViewModel(repository)

        lifecycleScope.launch {
            repository.saveMoviesFromJson()
        }

        setContent {
            FilmesTheme {
                val movies by viewModel.movies.collectAsState()
                MovieApp(
                    movies = movies
                )
            }
        }
    }
}
