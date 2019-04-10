package com.example.moviesguide.entities

class Video(
    var id: String,
    var name: String,
    var key: String? = null
) {
    companion object {
        const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }

    val url: String?
        get() = youTubeBaseUrl + key
}