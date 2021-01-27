package ru.maribobah.academyhomework.fragments.moviesList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.fragments.categories.MoviesListCategory

class MoviesListViewModelFactory(private val category: MoviesListCategory) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(category, Repository())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}