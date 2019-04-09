package com.example.moviesguide.api

import com.example.moviesguide.entities.Movie

object TrendingMoviesResponseModel {
    data class Result(val results: List<Movie>)
}