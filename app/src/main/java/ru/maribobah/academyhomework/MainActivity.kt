package ru.maribobah.academyhomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.maribobah.academyhomework.fragments.categories.CategoriesFragment
import ru.maribobah.academyhomework.fragments.categories.FragmentMoviesListClickListener
import ru.maribobah.academyhomework.fragments.movieItem.MovieItemFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, FragmentMoviesListClickListener {
    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_main, CategoriesFragment())
                .commit()
        }
    }

    override fun onClickMovieCard(movieId: Long) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container_main, MovieItemFragment.newInstance(movieId))
            .commit()
    }

    override fun onClickBack() {
        supportFragmentManager.popBackStack()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}

