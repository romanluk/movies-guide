package com.example.moviesguide.api

import com.example.moviesguide.entities.Video

object VideosResponseModel {
    data class Result(val results: List<Video>)
}