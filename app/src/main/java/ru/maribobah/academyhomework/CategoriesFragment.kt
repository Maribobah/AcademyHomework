package ru.maribobah.academyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.maribobah.academyhomework.data.models.Movie

class CategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.vp_movies)
        viewPager.adapter = MoviesListPagerAdapter(this)

        val tabStrategy =
            TabLayoutMediator.TabConfigurationStrategy() { tab: TabLayout.Tab, pos: Int ->
                tab.text = MoviesListPages.values()[pos].text
            }

        val tabLayout = view.findViewById<TabLayout>(R.id.tl_categories)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager, tabStrategy)
        tabLayoutMediator.attach()
    }
}

interface FragmentMoviesListClickListener {
    fun onClickMovieCard(movie: Movie)
    fun onClickBack()
}
