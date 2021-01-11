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
import ru.maribobah.academyhomework.data.models.Movie

class MovieListFragment : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private val viewModel: MoviesListViewModel by viewModels { ViewModelFactory() }
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieAdapter(clickListener = fragmentMoviesClickListener)
        initRecycler(view)
        viewModel.moviesList.observe(viewLifecycleOwner, this::updateMovieAdapter)
        viewModel.loadMovies()
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

    private fun updateMovieAdapter(movies: List<Movie>) {
        adapter.setData(movies)
    }
}

interface FragmentMoviesListClickListener {
    fun onClickMovieCard(movie: Movie)
    fun onClickBack()
}
