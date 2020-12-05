package ru.maribobah.academyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Movie

class FragmentMoviesList : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null

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
        val recycler: RecyclerView = view.findViewById(R.id.rv_movies)
        val adapter = MovieAdapter(DataUtil().generateMovies(), fragmentMoviesClickListener)
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
        val gridSize = resources.getInteger(R.integer.grid_size)
        recycler.layoutManager = GridLayoutManager(requireContext(), gridSize)
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(MovieSpaceItemDecoration(
                resources.getDimensionPixelSize(R.dimen.movie_card_padding),
                gridSize
        ))
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