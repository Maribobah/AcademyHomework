package ru.maribobah.academyhomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maribobah.academyhomework.data.Repository

class ViewModelFactory(val category: MoviesListPages = MoviesListPages.NOW_PLAYING) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(category, Repository())
        MovieItemViewModel::class.java -> MovieItemViewModel(Repository())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}