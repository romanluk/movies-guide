package com.example.moviesguide.entities

import com.example.moviesguide.prefs.MoviesPrefs
import com.google.gson.annotations.SerializedName

class Movie(
    var id: Int = 0,

    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,

    @SerializedName("title")
    var movieTitle: String? = null,

    @SerializedName("name")
    var seriesName: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null,

    @SerializedName("overview")
    var overview: String? = null
) {

    companion object {
        const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
    }

    val absolutePosterPath: String
        get() = posterBaseUrl + posterPath

    val absoluteBackdropPath: String
        get() = backdropBaseUrl + backdropPath

    val title: String
        get() {
            return when {
                movieTitle != null -> movieTitle!!
                seriesName != null -> seriesName!!
                else -> ""
            }
        }
    val isFavorite: Boolean
        get() {
            return MoviesPrefs.getMovie(id.toString()) != null
        }
}
