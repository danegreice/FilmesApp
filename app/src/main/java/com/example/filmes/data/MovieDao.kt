package com.example.filmes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.filmes.model.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM filmes")
    suspend fun getAllMovies(): List<MovieEntity>

    @Query("SELECT * FROM filmes WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}