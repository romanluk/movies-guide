package com.example.moviesguide.api

import com.example.moviesguide.App
import com.example.moviesguide.R
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDbApiService {
    @GET("trending/all/day")
    fun getTrendingMovies(@Query("api_key") apiKey: String = "23fd2cb5a330110d4c0c45359415e528")
            : Observable<TrendingMoviesResponseModel.Result>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String,
                     @Query("api_key") apiKey: String = "23fd2cb5a330110d4c0c45359415e528")
            : Observable<TrendingMoviesResponseModel.Result>


    companion object {
        fun create(): MoviesDbApiService {
            val retrofit = Retrofit.Builder().
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                    addConverterFactory(GsonConverterFactory.create()).
                    baseUrl("https://api.themoviedb.org/3/").build()
            return retrofit.create(MoviesDbApiService::class.java)
        }
    }
}