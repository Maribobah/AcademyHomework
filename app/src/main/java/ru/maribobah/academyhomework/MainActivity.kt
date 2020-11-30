package ru.maribobah.academyhomework

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(), FragmentMoviesListClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainContainer, FragmentMoviesList())
                .commit()
        }
    }

    override fun onClickMovieCard() {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.mainContainer, FragmentMoviesDetails())
            .commit()
    }

    override fun onClickBack() {
        supportFragmentManager.popBackStack()
    }

}

interface FragmentMoviesListClickListener {
    fun onClickMovieCard()
    fun onClickBack()
}