package com.example.moviesguide.details

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.lujun.androidtagview.TagContainerLayout
import com.example.moviesguide.R
import com.example.moviesguide.entities.Movie
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var backdropImageView: ImageView
    private lateinit var posterImageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var overviewView: TextView
    private lateinit var releaseDateView: TextView
    private lateinit var scoreView: TextView
    private lateinit var videosView: RecyclerView
    private lateinit var videosSectionView: View
    private lateinit var backButtonView: View
    private lateinit var tagsContainerView: TagContainerLayout
    private lateinit var favoriteButtonView: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        findViews()

        val posterImageUri = intent.getStringExtra(POSTER)
        val backdropImageUri = intent.getStringExtra(BACKDROP_IMAGE)
        val title = intent.getStringExtra(TITLE)
        val overview = intent.getStringExtra(OVERVIEW)
        val voteAverage = intent.getDoubleExtra(VOTE_AVERAGE, 0.0)

        Picasso.get().load(posterImageUri).into(posterImageView)
        Picasso.get().load(backdropImageUri).into(backdropImageView)
        titleView.text = title
        overviewView.text = overview
        scoreView.text = voteAverage.toString()

        backButtonView.setOnClickListener { finish() }
    }

    private fun findViews() {
        backdropImageView = findViewById(R.id.details_backdrop)
        posterImageView = findViewById(R.id.details_poster)
        titleView = findViewById(R.id.details_title)
        overviewView = findViewById(R.id.details_overview)
        releaseDateView = findViewById(R.id.details_release_date)
        scoreView = findViewById(R.id.details_score)
        videosView = findViewById(R.id.details_videos)
        backButtonView = findViewById(R.id.details_back_button)
        favoriteButtonView = findViewById(R.id.details_favorite_fab)
    }

    companion object {
        private const val MOVIE_ID: String = "extra_movie_id"
        private const val POSTER: String = "extra_poster"
        private const val BACKDROP_IMAGE: String = "extra_backdrop_image"
        private const val TITLE: String = "extra_title"
        private const val OVERVIEW: String = "extra_overview"
        private const val RELEASE_DATE: String = "extra_release_date"
        private const val VOTE_AVERAGE: String = "extra_vote_average"

        fun newIntent(context: Context, movieItem : Movie): Intent {
            val i = Intent(context, MovieDetailsActivity::class.java)
            i.putExtra(MOVIE_ID, movieItem.id)
            i.putExtra(POSTER, movieItem.absolutePosterPath)
            i.putExtra(BACKDROP_IMAGE, movieItem.absoluteBackdropPath)
            i.putExtra(TITLE, movieItem.title)
            i.putExtra(OVERVIEW, movieItem.overview)
            i.putExtra(RELEASE_DATE, movieItem.releaseDate)
            i.putExtra(VOTE_AVERAGE, movieItem.voteAverage)
            return i
        }
    }
}
