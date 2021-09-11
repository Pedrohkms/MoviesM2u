package br.com.pedro.moviesm2u.ui.movieDetail

import androidx.lifecycle.*
import br.com.pedro.moviesm2u.data.entities.Movie
import br.com.pedro.moviesm2u.data.local.MovieDao
import br.com.pedro.moviesm2u.data.repository.MovieRepository
import br.com.pedro.moviesm2u.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val dao: MovieDao
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _movie = _id.switchMap { id ->
        repository.getMovie(id)
    }

    val movie: LiveData<Resource<Movie>> = _movie


    fun start(id: Int) {
        _id.value = id
    }

    fun updateFav(isFav : Boolean, id : Int){
        viewModelScope.launch {
            dao.updateFav(isFav, id)
        }
    }

}