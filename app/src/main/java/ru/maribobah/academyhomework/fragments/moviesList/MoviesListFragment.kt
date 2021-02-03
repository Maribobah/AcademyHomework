package ru.maribobah.academyhomework.fragments.moviesList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.R
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener
import ru.maribobah.academyhomework.data.models.MoviesListCategory

class MovieListFragment : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private lateinit var category: MoviesListCategory

    private val viewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(
            requireContext(),
            category
        )
    }
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoryPosition = arguments?.getInt(CATEGORY_FIELD)
            ?: throw IllegalArgumentException("Can't find \"${CATEGORY_FIELD}\" argument")
        category = MoviesListCategory.values()[categoryPosition]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(clickListener = fragmentMoviesClickListener)
        initRecycler(view)
        viewModel.moviesList.observe(viewLifecycleOwner, this::updateMovieAdapter)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListClickListener) {
            fragmentMoviesClickListener = context
        }
    }

    override fun onDetach() {
        fragmentMoviesClickListener = null
        super.onDetach()
    }

    private fun initRecycler(view: View) {
        val recycler: RecyclerView = view.findViewById(R.id.rv_movies)
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
        val gridSize = resources.getInteger(R.integer.grid_size)
        recycler.layoutManager = GridLayoutManager(requireContext(), gridSize)
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(
            MovieSpaceItemDecoration(
                resources.getDimensionPixelSize(R.dimen.movie_card_padding),
                gridSize
            )
        )
    }

    private fun updateMovieAdapter(movies: List<MovieEntity>) {
        adapter.setData(movies)
    }

    companion object {
        private const val CATEGORY_FIELD = "category"
        fun newInstance(category: Int): MovieListFragment {
            val args = Bundle()
            args.putInt(CATEGORY_FIELD, category)
            val fragment = MovieListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}