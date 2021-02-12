package ru.maribobah.academyhomework.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.maribobah.academyhomework.fragments.movieItem.MovieItemFragment
import ru.maribobah.academyhomework.fragments.moviesList.MovieListFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMovieItemFragment() : MovieItemFragment
    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment() : MovieListFragment

}