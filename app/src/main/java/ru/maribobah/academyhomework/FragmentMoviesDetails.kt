package ru.maribobah.academyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.maribobah.academyhomework.data.models.Movie

class FragmentMoviesDetails() : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable<Movie>("movie")!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<TextView>(R.id.tv_rate_details).text = movie.rate
        view.findViewById<TextView>(R.id.tv_title_details).text = movie.name
        view.findViewById<TextView>(R.id.tv_genre_details).text = movie.genres
        view.findViewById<TextView>(R.id.tv_reviews_details).text = movie.reviewsStr
        view.findViewById<TextView>(R.id.tv_storyline_details).text = movie.storyline

        view.findViewById<ImageView>(R.id.iv_poster_details).setImageResource(movie.posterDetails)

        for (i in 1..5) {
            val idIconView = resources.getIdentifier("ic_star${i}_details", "id", context?.packageName)
            movie.setTintColorForStar(view.findViewById(idIconView), i)
        }

        view.findViewById<View>(R.id.backButton)?.setOnClickListener{
                fragmentMoviesClickListener?.onClickBack()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler: RecyclerView = view.findViewById(R.id.rv_cast)
        val adapter = CastMovieAdapter(movie.actors)
        adapter.setHasStableIds(true)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recycler.setHasFixedSize(true)
        recycler.addItemDecoration(CastMovieSpaceItemDecoration(
                resources.getDimensionPixelSize(R.dimen.cast_movie_space)))
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

    companion object {
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val args = Bundle()
            args.putParcelable("movie", movie)
            val fragment = FragmentMoviesDetails()
            fragment.arguments = args
            return fragment
        }
    }

}

