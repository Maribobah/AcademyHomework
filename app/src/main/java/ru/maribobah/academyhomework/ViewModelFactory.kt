package ru.maribobah.academyhomework

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.fragments.movieItem.MovieItemViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieItemViewModel::class.java -> MovieItemViewModel(Repository(context))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}