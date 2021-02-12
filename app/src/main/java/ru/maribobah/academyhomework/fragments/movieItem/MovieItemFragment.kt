package ru.maribobah.academyhomework.fragments.movieItem

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.maribobah.academyhomework.*
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.di.Injectable
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener
import javax.inject.Inject

class MovieItemFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var fragmentMoviesClickListener: FragmentMoviesListClickListener? = null
    private val viewModel: MovieItemViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: MovieCastAdapter
    private var idMovie: Long = -1

    private var recycler: RecyclerView? = null
    private lateinit var layout: ConstraintLayout
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
        idMovie = arguments?.getLong(ID_FIELD)
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
        layout = view.findViewById(R.id.cl_movie_details)
        tvRate = view.findViewById(R.id.tv_rate_details)
        tvTitle = view.findViewById(R.id.tv_title_details)
        tvGenre = view.findViewById(R.id.tv_genre_details)
        tvReviews = view.findViewById(R.id.tv_reviews_details)
        tvStoryline = view.findViewById(R.id.tv_storyline_details)
        rbRating = view.findViewById(R.id.rb_rating)
        ivPoster = view.findViewById(R.id.iv_poster_details)
        tvHeadCast = view.findViewById(R.id.tv_head_cast)
    }

    private fun updateMovie(movieEntity: MovieEntity?) {
        movieEntity?.let { movie ->
            tvRate.text = movie.rate
            tvTitle.text = movie.name
            tvGenre.text = movie.genres
            tvReviews.text = movie.reviews
            tvStoryline.text = movie.storyline
            rbRating.rating = movie.stars

            Glide.with(this).load(movie.backdrop).into(ivPoster)
        } ?: run {
            Snackbar.make(layout, "Can't find the movie", Snackbar.LENGTH_SHORT).show()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun updateActors(actors: List<ActorEntity>) {
        if (actors.isEmpty()) {
            tvHeadCast.visibility = View.GONE
            recycler?.visibility = View.GONE
        } else {
            tvHeadCast.visibility = View.VISIBLE
            recycler?.visibility = View.VISIBLE
            adapter.setData(actors)
        }
    }

    companion object {
        private const val ID_FIELD = "id"
        fun newInstance(id: Long): MovieItemFragment {
            val args = Bundle()
            val fragment = MovieItemFragment()
            args.putLong(ID_FIELD, id)
            fragment.arguments = args
            return fragment
        }
    }
}

