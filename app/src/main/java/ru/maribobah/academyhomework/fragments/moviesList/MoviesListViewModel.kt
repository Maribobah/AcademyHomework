package ru.maribobah.academyhomework.fragments.moviesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.data.models.MoviesListCategory

class MoviesListViewModel(
    private val category: MoviesListCategory,
    private val repository: Repository
) : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<MovieEntity>>(emptyList())
    val moviesList: LiveData<List<MovieEntity>> get() = _mutableMoviesList

    private val loadExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MoviesListViewModel", "Failed load movies.", throwable)
    }

    init {
        fetchMoviesFromDB()
        updateMoviesFromNetwork()
    }

    private fun fetchMoviesFromDB() {
        viewModelScope.launch(loadExceptionHandler) {
            _mutableMoviesList.value = repository.getMovies(category, Repository.Type.DB)
        }
    }

    private fun updateMoviesFromNetwork() {
        viewModelScope.launch(loadExceptionHandler) {
            val movies = repository.getMovies(category, Repository.Type.NETWORK)
            _mutableMoviesList.value = movies
            repository.saveMovies(movies, category)
        }
    }
}