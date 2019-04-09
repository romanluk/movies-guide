package com.example.moviesguide

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.moviesguide.details.MovieDetailsActivity
import com.example.moviesguide.entities.Movie
import com.example.moviesguide.liked.LikedMoviesFragment
import com.example.moviesguide.prefs.MoviesPrefs
import com.example.moviesguide.trending.TrendingMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TrendingMoviesFragment.OnListFragmentInteractionListener, LikedMoviesFragment.OnListFragmentInteractionListener {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_trending -> {
                val trendingMoviesFragment = TrendingMoviesFragment.newInstance(2)
                openFragment(trendingMoviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_liked -> {
                val likedMoviesFragment = LikedMoviesFragment.newInstance(1)
                openFragment(likedMoviesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                message.setText(R.string.title_search)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MoviesPrefs.init(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val trendingMoviesFragment = TrendingMoviesFragment.newInstance(2)
        openFragment(trendingMoviesFragment)
    }

    override fun onListFragmentInteraction(movieItem: Movie) {
        startActivity(MovieDetailsActivity.newIntent(this, movieItem))
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
