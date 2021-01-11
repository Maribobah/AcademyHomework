package ru.maribobah.academyhomework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.TmdbApi
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie

class MovieItemViewModel() : ViewModel() {

    private val _mutableMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie
    private val _mutableActorsList = MutableLiveData<List<Actor>>()
    val actorsList: LiveData<List<Actor>> get() = _mutableActorsList

    private val tmdbApi = TmdbApi()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MovieItemViewModel", "Failed load movie. Error: $throwable", throwable)
    }

    fun loadMovie(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            val movieDetails = tmdbApi.services.movieDetails(id)
            val imagesInfo = tmdbApi.services.configuration().images
            movieDetails.backdrop = imagesInfo.getFullBackdropPath(movieDetails.backdrop)
            movieDetails.poster = imagesInfo.getFullPosterPath(movieDetails.poster)
            _mutableMovie.value = movieDetails
            val credits = tmdbApi.services.movieCredits(id)
            val actors = credits.actors.map {
                it.avatar = imagesInfo.getFullProfilePath(it.avatar)
                return@map it
            }
            _mutableActorsList.value = actors
        }
    }
}