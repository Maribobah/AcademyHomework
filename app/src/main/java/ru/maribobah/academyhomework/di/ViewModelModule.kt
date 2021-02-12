package ru.maribobah.academyhomework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.maribobah.academyhomework.ViewModelFactory
import ru.maribobah.academyhomework.fragments.movieItem.MovieItemViewModel
import ru.maribobah.academyhomework.fragments.moviesList.MoviesListViewModel

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesListViewModel::class)
    abstract fun bindMovieListViewModel(moviesListViewModel: MoviesListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieItemViewModel::class)
    abstract fun bindMovieItemViewModel(movieItemViewModel: MovieItemViewModel) : ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}