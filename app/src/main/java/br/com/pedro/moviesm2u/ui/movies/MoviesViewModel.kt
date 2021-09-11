package br.com.pedro.moviesm2u.ui.movies

import androidx.lifecycle.ViewModel
import br.com.pedro.moviesm2u.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movies = repository.getMovies()
    val moviesFav = repository.getMoviesFav()
}
