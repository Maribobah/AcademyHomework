package ru.maribobah.academyhomework

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviesListPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = MoviesListPages.values().size

    override fun createFragment(position: Int): Fragment {
        return MovieListFragment.newInstance(position)
    }
}

enum class MoviesListPages(val text: String) {
    NOW_PLAYING("Now Playing"),
    UPCOMING("Coming Soon"),
    TOP_RATED("Top Rated"),
    POPULAR("Popular")
}