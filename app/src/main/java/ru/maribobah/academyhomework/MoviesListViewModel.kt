package ru.maribobah.academyhomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.models.Movie

class MoviesListViewModel(
    private val category : MoviesListPages,
    private val repository: Repository
) : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MoviesListViewModel", "Failed load movies.", throwable)
    }

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(exceptionHandler) {
            _mutableMoviesList.value = when(category) {
                MoviesListPages.NOW_PLAYING -> TODO()
                MoviesListPages.UPCOMING -> TODO()
                MoviesListPages.POPULAR -> repository.getPopularMovies()
                MoviesListPages.TOP_RATED -> TODO()
            }
        }
    }
}