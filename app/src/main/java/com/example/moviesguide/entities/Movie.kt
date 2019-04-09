package com.example.moviesguide.entities

import com.google.gson.annotations.SerializedName

class Movie(
    var id: Int = 0,

    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @SerializedName("video")
    var video: Boolean = false,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("title")
    var title: String,

    @SerializedName("popularity")
    var popularity: Double = 0.0,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("adult")
    var adult: Boolean = false,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("overview")
    var overview: String? = null,

    var details: MovieDetails? = null,

    var isFavorite: Boolean = false
) {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }

    val absolutePosterPath: String
        get() = posterBaseUrl + posterPath

    val absoluteBackdropPath: String
        get() = backdropBaseUrl + backdropPath
}
