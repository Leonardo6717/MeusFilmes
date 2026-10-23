package com.example.meusfilmes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil3.load
import com.example.meusfilmes.databinding.ActivityEditMovieBinding
import com.example.meusfilmes.viewmodel.MovieViewModel
import java.util.Locale
import coil3.request.placeholder
import coil3.request.error

class EditMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "movie_id"
    }

    private lateinit var binding: ActivityEditMovieBinding
    private val viewModel: MovieViewModel by viewModels()
    private var movieId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieId = intent.getIntExtra(EXTRA_MOVIE_ID, -1)
        val movie = viewModel.getMovie(movieId)
        if (movie == null) {
            Toast.makeText(this, "Filme não encontrado.", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        binding.titleInput.setText(movie.title)
        binding.ratingInput.setText(String.format(Locale.US, "%.1f", movie.rating))
        binding.imageUrlInput.setText(movie.imageUrl)
        loadPreview(movie.imageUrl)

        binding.previewButton.setOnClickListener {
            val url = binding.imageUrlInput.text?.toString()?.trim().orEmpty()
            if (url.startsWith("https://") || url.startsWith("http://")) {
                loadPreview(url)
            } else {
                binding.imageUrlInput.error = "Informe uma URL válida (http ou https)."
            }
        }

        binding.saveButton.setOnClickListener {
            val title = binding.titleInput.text?.toString().orEmpty()
            val ratingText = binding.ratingInput.text?.toString().orEmpty().replace(',', '.')
            val rating = ratingText.toFloatOrNull()
            val imageUrl = binding.imageUrlInput.text?.toString().orEmpty().trim()

            when {
                title.isBlank() -> binding.titleInput.error = "Informe o nome do filme."
                rating == null || rating !in 0f..10f ->
                    binding.ratingInput.error = "A nota deve estar entre 0 e 10."
                !(imageUrl.startsWith("https://") || imageUrl.startsWith("http://")) ->
                    binding.imageUrlInput.error = "Informe uma URL de imagem válida."
                viewModel.updateMovie(movieId, title, rating, imageUrl) -> finish()
                else -> Toast.makeText(this, "Não foi possível salvar.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadPreview(url: String) {
        binding.previewImage.load(url) {
            placeholder(
                android.graphics.drawable.ColorDrawable(
                    android.graphics.Color.DKGRAY
                )
            )
            error(
                android.graphics.drawable.ColorDrawable(
                    android.graphics.Color.RED
                )
            )
        }
    }
}
