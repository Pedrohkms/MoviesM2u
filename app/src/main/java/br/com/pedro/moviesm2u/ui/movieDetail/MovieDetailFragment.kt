package br.com.pedro.moviesm2u.ui.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.pedro.moviesm2u.R
import br.com.pedro.moviesm2u.data.entities.Movie
import br.com.pedro.moviesm2u.databinding.MovieDetailFragmentBinding
import br.com.pedro.moviesm2u.utils.Resource
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner, {
            it?.data?.let { itMovie -> bindMovie(itMovie) }
            binding.progressBar.visibility = View.GONE
        })
    }

    private fun bindMovie(movie: Movie) {
        binding.movieTitleDescriptionView.text = movie.title
        binding.releaseDate.text = movie.release_date
        binding.popularity.text = movie.popularity.toString()
        binding.note.text = movie.vote_average.toString()
        binding.description.text = movie.overview
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w342${movie.poster_path}")
            .into(binding.imageCoverDescription)

        if (!movie.isFavorited) {
            binding.favButton.setOnClickListener {
                viewModel.updateFav(true, movie.id)
                binding.favButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_favorite_24,
                    0,
                    0,
                    0
                )
                binding.favButton.text = "Favorito"
            }
        } else {
            binding.favButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_baseline_favorite_24,
                0,
                0,
                0
            )
            binding.favButton.text = "Favorito"
            binding.favButton.setOnClickListener {
                viewModel.updateFav(false, movie.id)
                binding.favButton.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_favorite_border_24,
                    0,
                    0,
                    0
                )
                binding.favButton.text = "Favoritar"
            }
        }
    }
}