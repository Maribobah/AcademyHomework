package ru.maribobah.academyhomework

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import loadMoviesFromAssets
import ru.maribobah.academyhomework.data.models.Movie

class MoviesListViewModel(val context: Context) : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("MoviesListViewModel", "Failed load movies. Message: $throwable", throwable)
    }

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(exceptionHandler) {
            _mutableMoviesList.value = loadMoviesFromAssets(context)
        }
    }
}