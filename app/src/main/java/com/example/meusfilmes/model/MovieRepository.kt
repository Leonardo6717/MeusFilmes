package com.example.meusfilmes.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repositório em memória compartilhado pelas telas.
 * Em um app completo, esta camada pode ser substituída por Room.
 */
object MovieRepository {
    private val _movies = MutableStateFlow(
        listOf(
            Movie(1, "Interestelar", 9.0f, "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg"),
            Movie(2, "A Origem", 8.8f, "https://image.tmdb.org/t/p/w500/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg"),
            Movie(3, "Homem-Aranha: Através do Aranhaverso", 9.1f, "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj765B.jpg")
        )
    )
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    fun getMovie(id: Int): Movie? = _movies.value.find { it.id == id }

    fun updateMovie(id: Int, title: String, rating: Float, imageUrl: String): Boolean {
        if (title.isBlank() || rating !in 0f..10f || imageUrl.isBlank()) return false
        val existing = getMovie(id) ?: return false
        _movies.value = _movies.value.map { movie ->
            if (movie.id == id) existing.copy(
                title = title.trim(),
                rating = rating,
                imageUrl = imageUrl.trim()
            ) else movie
        }
        return true
    }
}
