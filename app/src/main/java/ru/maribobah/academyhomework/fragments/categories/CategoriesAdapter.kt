package ru.maribobah.academyhomework.fragments.categories

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.maribobah.academyhomework.data.models.MoviesListCategory
import ru.maribobah.academyhomework.fragments.moviesList.MovieListFragment

class MoviesListPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = MoviesListCategory.values().size

    override fun createFragment(position: Int): Fragment {
        return MovieListFragment.newInstance(position)
    }
}