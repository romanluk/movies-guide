package com.example.moviesguide.prefs

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.view.MotionEventCompat
import com.example.moviesguide.entities.Movie
import com.google.gson.Gson

object MoviesPrefs {
    private const val NAME = "om.example.moviesguide.prefs.MoviesPrefs"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
        editor = preferences.edit()
    }

    fun addMovie(movie: Movie) {
        val json = Gson().toJson(movie)
        editor.putString(movie.id.toString(), json)
        editor.commit()
    }

    fun getMovie(id: String) : Movie? {
        val jsonStr = preferences.getString(id, "")
        return if (jsonStr.isNotEmpty()) {
            Gson().fromJson(jsonStr, Movie::class.java)
        } else {
            null
        }
    }

    fun deleteMovie(movie: Movie) {
        editor.remove(movie.id.toString())
        editor.commit()
    }

    fun getAllMovies(): List<Movie> {
        val res: MutableList<Movie> = mutableListOf<Movie>()
        val all = preferences.all.values
        for(movieJson in all) {
            res.add(Gson().fromJson(movieJson.toString(), Movie::class.java))
        }
        return res
    }
}