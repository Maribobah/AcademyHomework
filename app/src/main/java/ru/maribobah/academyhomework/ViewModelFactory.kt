package ru.maribobah.academyhomework

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel()
        MovieItemViewModel::class.java -> MovieItemViewModel()
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}