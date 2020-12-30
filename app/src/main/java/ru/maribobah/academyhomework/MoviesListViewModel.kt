package ru.maribobah.academyhomework

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import loadMoviesFromAssets
import ru.maribobah.academyhomework.data.models.Movie

class MoviesListViewModel : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    fun loadMovies(context: Context) {
        viewModelScope.launch {
            _mutableMoviesList.value = loadMoviesFromAssets(context)
        }
    }
}