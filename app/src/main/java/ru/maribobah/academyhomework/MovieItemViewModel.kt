package ru.maribobah.academyhomework

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import findMovieInAssets
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.maribobah.academyhomework.data.models.Movie

class MovieItemViewModel(val context: Context) : ViewModel() {

    private val _mutableMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("LoadMovie", "Failed load movie. Context: $coroutineContext")
        throwable.printStackTrace()
    }

    fun loadMovie(id: Int) {
        viewModelScope.launch(exceptionHandler) {
            _mutableMovie.value = findMovieInAssets(context, id)
        }
    }
}