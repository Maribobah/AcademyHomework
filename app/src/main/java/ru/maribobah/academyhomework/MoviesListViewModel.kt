package ru.maribobah.academyhomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.TmdbApi
import ru.maribobah.academyhomework.data.models.Movie

class MoviesListViewModel(private val category : MoviesListPages) : ViewModel() {

    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    private val tmdbApi = TmdbApi()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MoviesListViewModel", "Failed load movies. Message: $throwable", throwable)
    }

    init {
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch(exceptionHandler) {
            val imagesInfo = tmdbApi.services.configuration().images
            val service = when (category) {
                MoviesListPages.NOW_PLAYING -> tmdbApi.services::nowPlayingMovies
                MoviesListPages.UPCOMING -> tmdbApi.services::upcomingMovies
                MoviesListPages.POPULAR -> tmdbApi.services::popularMovies
                MoviesListPages.TOP_RATED -> tmdbApi.services::topRatedMovies
            }
            val res = service.invoke()
            val movies = res.movies.map {
                val movie = tmdbApi.services.movieDetails(it.id)
                movie.backdrop = imagesInfo.getFullBackdropPath(movie.backdrop)
                movie.poster = imagesInfo.getFullPosterPath(movie.poster)
                return@map movie
            }
            _mutableMoviesList.value = movies
        }
    }
}