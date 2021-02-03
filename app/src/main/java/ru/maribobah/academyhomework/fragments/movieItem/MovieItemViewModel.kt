package ru.maribobah.academyhomework.fragments.movieItem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity

class MovieItemViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _mutableMovie = MutableLiveData<MovieEntity>()
    val movie: LiveData<MovieEntity> get() = _mutableMovie
    private val _mutableActors = MutableLiveData<List<ActorEntity>>()
    val actors: LiveData<List<ActorEntity>> get() = _mutableActors

    private var movieId: Long = -1

    private val loadMovieExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MovieItemViewModel", "Failed load movie.", throwable)
    }

    private val loadActorsExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MovieItemViewModel", "Failed load actors.", throwable)
    }

    fun loadMovie(id: Long) {
        movieId = id
        getMovieDetails()
        fetchActorsFromDB()
        updateActorsFromNetwork()
    }

    private fun getMovieDetails() {
        viewModelScope.launch(loadMovieExceptionHandler) {
            _mutableMovie.value = repository.getMovieDetails(movieId)
        }
    }

    private fun fetchActorsFromDB() {
        viewModelScope.launch(loadActorsExceptionHandler) {
            _mutableActors.value = repository.getMovieActors(movieId, Repository.Type.DB)
        }
    }

    private fun updateActorsFromNetwork() {

        viewModelScope.launch(loadActorsExceptionHandler) {
            val actorsNetwork = repository.getMovieActors(movieId, Repository.Type.NETWORK)
            _mutableActors.value = actorsNetwork
            repository.saveActors(actorsNetwork, movieId)
        }
    }
}