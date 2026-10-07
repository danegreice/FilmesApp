package com.example.filmes

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmes.model.Movie

@Composable
fun MovieApp(
    movies: List<Movie>
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "movies"
    ) {
        composable("movies") {
            MovieListScreen(
                movies = movies,
                onMovieClick = { movieId ->
                    navController.navigate(
                        "movie/$movieId"
                    )
                }
            )
        }

        composable(
            route = "movie/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
            ) { backStackEntry ->

            val movieId = backStackEntry.arguments?.getInt("movieId")

            val movie =
                movies.find {
                    it.id == movieId
                }

            if (movie != null) {
                MovieDetailScreen(
                    movie = movie,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}