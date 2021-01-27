package ru.maribobah.academyhomework.fragments.movieItem

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie

class MovieItemViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _mutableMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie
    private val _mutableActors = MutableLiveData<List<Actor>>()
    val actors: LiveData<List<Actor>> get() = _mutableActors

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MovieItemViewModel", "Failed load movie.", throwable)
    }

    fun loadMovie(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            val movieDetails = repository.getMovieDetails(id)
            _mutableMovie.value = movieDetails
            _mutableActors.value = repository.getMovieActors(id)
        }
    }
}