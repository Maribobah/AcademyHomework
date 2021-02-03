package ru.maribobah.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.fragments.categories.CategoriesFragment
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener
import ru.maribobah.academyhomework.fragments.movieItem.MovieItemFragment

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main, CategoriesFragment())
                .commit()
        }
    }

    override fun onClickMovieCard(movie: MovieEntity) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container_main, MovieItemFragment.newInstance(movie.id))
            .commit()
    }

    override fun onClickBack() {
        supportFragmentManager.popBackStack()
    }

}

