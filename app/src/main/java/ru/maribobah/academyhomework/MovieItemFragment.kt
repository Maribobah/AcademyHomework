package ru.maribobah.academyhomework

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.maribobah.academyhomework.data.models.Movie

class MovieItemFragment() : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private val viewModel: MovieItemViewModel by viewModels { ViewModelFactory() }
    private lateinit var adapter: MovieCastAdapter
    private var idMovie: Int = -1

    private var recycler: RecyclerView? = null
    private var tvRate: TextView? = null
    private var tvTitle: TextView? = null
    private var tvGenre: TextView? = null
    private var tvReviews: TextView? = null
    private var tvStoryline: TextView? = null
    private var rbRating: RatingBarSvg? = null
    private var ivPoster: ImageView? = null
    private var tvHeadCast: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idMovie = arguments?.getInt("id")
            ?: throw IllegalArgumentException("Can't find \"id\" argument")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MovieCastAdapter()
        initRecycler(view)
        initViews(view)
        viewModel.movie.observe(viewLifecycleOwner, this::updateMovie)
        viewModel.loadMovie(requireContext(), idMovie)
        view.findViewById<View>(R.id.btn_back)?.setOnClickListener {
            fragmentMoviesClickListener?.onClickBack()
        }
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

    override fun onDestroyView() {
        recycler?.adapter = null
        recycler = null
        tvRate = null
        tvTitle = null
        tvGenre = null
        tvReviews = null
        tvStoryline = null
        rbRating = null
        ivPoster = null
        tvHeadCast = null
        super.onDestroyView()
    }

    private fun initRecycler(view: View) {
        recycler = view.findViewById(R.id.rv_cast)
        recycler?.let {
            adapter.setHasStableIds(true)
            it.adapter = adapter
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.setHasFixedSize(true)
            it.addItemDecoration(
                MovieCastSpaceItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.cast_movie_space)
                )
            )
        }
    }

    private fun initViews(view: View) {
        tvRate = view.findViewById(R.id.tv_rate_details)
        tvTitle = view.findViewById(R.id.tv_title_details)
        tvGenre = view.findViewById(R.id.tv_genre_details)
        tvReviews = view.findViewById(R.id.tv_reviews_details)
        tvStoryline = view.findViewById(R.id.tv_storyline_details)
        rbRating = view.findViewById(R.id.rb_rating)
        ivPoster = view.findViewById(R.id.iv_poster_details)
        tvHeadCast = view.findViewById(R.id.tv_head_cast)
    }

    private fun updateMovie(movie: Movie) {
        tvRate?.text = movie.rate
        tvTitle?.text = movie.name
        tvGenre?.text = MoviePresentation.genresPresentation(movie.genres)
        tvReviews?.text = MoviePresentation.reviewsPresentation(movie.reviews)
        tvStoryline?.text = movie.storyline
        rbRating?.rating = MoviePresentation.starsFormat(movie.stars)

        ivPoster?.let {
            Glide.with(this).load(movie.backdrop).into(it)
        }

        if (movie.actors.isEmpty()) {
            tvHeadCast?.visibility = View.GONE
            recycler?.visibility = View.GONE
        } else {
            adapter.setData(movie.actors)
        }
    }

    companion object {
        fun newInstance(id: Int): MovieItemFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = MovieItemFragment()
            fragment.arguments = args
            return fragment
        }
    }

}

