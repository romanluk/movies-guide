package com.example.moviesguide.entities

import com.google.gson.annotations.SerializedName

class Movie(
    var id: Int = 0,

    @SerializedName("vote_count")
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var title: String,
    var popularity: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    var originalLanguage: String,
    var originalTitle: String,
    var backdropPath: String? = null,
    var adult: Boolean = false,
    var releaseDate: String,
    var details: MovieDetails? = null,
    var isFavorite: Boolean = false,
    var overview: String? = null
) {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }

    val absolutePosterPath: String
        get() = posterBaseUrl + posterPath
}
