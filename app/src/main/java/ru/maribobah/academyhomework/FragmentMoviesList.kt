package ru.maribobah.academyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import loadMovies
import ru.maribobah.academyhomework.data.models.Movie

class FragmentMoviesList : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private lateinit var movies: List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            movies = loadMovies(requireContext())
            val recycler: RecyclerView = view.findViewById(R.id.rv_movies)
            val adapter = MovieAdapter(movies, fragmentMoviesClickListener)
            adapter.setHasStableIds(true)
            recycler.adapter = adapter
            val gridSize = resources.getInteger(R.integer.grid_size)
            recycler.layoutManager = GridLayoutManager(requireContext(), gridSize)
            recycler.setHasFixedSize(true)
            recycler.addItemDecoration(MovieSpaceItemDecoration(
                resources.getDimensionPixelSize(R.dimen.movie_card_padding),
                gridSize
            )
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMoviesListClickListener) {
            fragmentMoviesClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentMoviesClickListener = null
    }
}

interface FragmentMoviesListClickListener {
    fun onClickMovieCard(movie: Movie)
    fun onClickBack()
}