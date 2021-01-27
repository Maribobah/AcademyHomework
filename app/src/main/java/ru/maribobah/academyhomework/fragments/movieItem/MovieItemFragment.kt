package ru.maribobah.academyhomework.fragments.movieItem

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
import ru.maribobah.academyhomework.*
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener

class MovieItemFragment : Fragment() {

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private val viewModel: MovieItemViewModel by viewModels { ViewModelFactory() }
    private lateinit var adapter: MovieCastAdapter
    private var idMovie: Int = -1

    private var recycler: RecyclerView? = null
    private lateinit var tvRate: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvGenre: TextView
    private lateinit var tvReviews: TextView
    private lateinit var tvStoryline: TextView
    private lateinit var rbRating: RatingBarSvg
    private lateinit var ivPoster: ImageView
    private lateinit var tvHeadCast: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idMovie = arguments?.getInt(ID_FIELD)
            ?: throw IllegalArgumentException("Can't find \"$ID_FIELD\" argument")
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
        viewModel.actors.observe(viewLifecycleOwner, this::updateActors)
        viewModel.loadMovie(idMovie)
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
        super.onDestroyView()
    }

    private fun initRecycler(view: View) {
        adapter.setHasStableIds(true)
        recycler = view.findViewById(R.id.rv_cast)
        recycler?.adapter = adapter
        recycler?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            addItemDecoration(
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
        tvRate.text = movie.rate
        tvTitle.text = movie.name
        tvGenre.text = movie.genresPresentation
        tvReviews.text = movie.reviews
        tvStoryline.text = movie.storyline
        rbRating.rating = movie.stars

        Glide.with(this).load(movie.backdrop).into(ivPoster)
    }

    private fun updateActors(actors: List<Actor>) {
        if (actors.isEmpty()) {
            tvHeadCast.visibility = View.GONE
            recycler?.visibility = View.GONE
        } else {
            adapter.setData(actors)
        }
    }

    companion object {
        private const val ID_FIELD = "id"
        fun newInstance(id: Int): MovieItemFragment {
            val args = Bundle()
            args.putInt(ID_FIELD, id)
            val fragment = MovieItemFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

