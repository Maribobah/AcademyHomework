package ru.maribobah.academyhomework.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.models.MoviesListCategory

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
            TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, pos: Int ->
                tab.text = MoviesListCategory.values()[pos].text
            }

        val tabLayout = view.findViewById<TabLayout>(R.id.tl_categories)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager, tabStrategy)
        tabLayoutMediator.attach()
    }
}

interface FragmentMoviesListClickListener {
    fun onClickMovieCard(movieId: Long)
    fun onClickBack()
}
