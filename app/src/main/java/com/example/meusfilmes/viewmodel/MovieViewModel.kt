package com.example.meusfilmes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.meusfilmes.model.Movie
import com.example.meusfilmes.model.MovieRepository
import kotlinx.coroutines.flow.StateFlow

class MovieViewModel : ViewModel() {
    val movies: StateFlow<List<Movie>> = MovieRepository.movies

    fun getMovie(id: Int): Movie? = MovieRepository.getMovie(id)

    fun updateMovie(id: Int, title: String, rating: Float, imageUrl: String): Boolean =
        MovieRepository.updateMovie(id, title, rating, imageUrl)
}
