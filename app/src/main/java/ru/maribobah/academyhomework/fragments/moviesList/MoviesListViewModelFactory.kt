package ru.maribobah.academyhomework.fragments.moviesList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.models.MoviesListCategory

class MoviesListViewModelFactory(
    private val context: Context,
    private val category: MoviesListCategory
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListViewModel::class.java -> MoviesListViewModel(category, Repository(context))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}