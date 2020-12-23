package ru.maribobah.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.maribobah.academyhomework.data.models.Movie

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main, FragmentMoviesList())
                .commit()
        }
    }

    override fun onClickMovieCard(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container_main, FragmentMoviesDetails.newInstance(movie))
            .commit()
    }

    override fun onClickBack() {
        supportFragmentManager.popBackStack()
    }

}

