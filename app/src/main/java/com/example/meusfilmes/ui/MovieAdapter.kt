package com.example.meusfilmes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.meusfilmes.databinding.ItemMovieBinding
import com.example.meusfilmes.model.Movie
import java.util.Locale

class MovieAdapter(
    private val onEdit: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = emptyList()

    fun submitList(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.movieRating.text = "★ ${String.format(Locale.getDefault(), "%.1f", movie.rating)} / 10"
            binding.movieImage.load(movie.imageUrl)
            binding.editButton.setOnClickListener { onEdit(movie) }
            binding.root.setOnClickListener { onEdit(movie) }
        }
    }
}
