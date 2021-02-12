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
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val repository: Repository) : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<MovieEntity>>(emptyList())
    val moviesList: LiveData<List<MovieEntity>> = _mutableMoviesList

    private val _category = MutableLiveData<MoviesListCategory>()
    val category: LiveData<MoviesListCategory> = _category

    private val loadExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MoviesListViewModel", "Failed load movies.", throwable)
    }

    fun loadMovies(moviesCategory: MoviesListCategory)
    {
        _category.value = moviesCategory
        fetchMoviesFromDB()
        updateMoviesFromNetwork()
    }

    private fun fetchMoviesFromDB() {
        category.value?.let { movieCategory ->
            viewModelScope.launch(loadExceptionHandler) {
                _mutableMoviesList.value = repository.getMovies(movieCategory, Repository.Type.DB)
            }
        }
    }

    private fun updateMoviesFromNetwork() {
        category.value?.let { movieCategory ->

            viewModelScope.launch(loadExceptionHandler) {
                val movies = repository.getMovies(movieCategory, Repository.Type.NETWORK)
                _mutableMoviesList.value = movies
                repository.saveMovies(movies, movieCategory)
            }
        }
    }
}