package ru.maribobah.academyhomework.fragments.moviesList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.models.Movie
import ru.maribobah.academyhomework.fragments.categories.MoviesListCategory

class MoviesListViewModel(
    private val category: MoviesListCategory,
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
            _mutableMoviesList.value = repository.getMovies(category)
        }
    }
}