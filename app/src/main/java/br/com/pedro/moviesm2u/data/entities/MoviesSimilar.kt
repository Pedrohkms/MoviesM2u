package br.com.pedro.moviesm2u.data.entities

import androidx.room.PrimaryKey

data class MoviesSimilar(
    @PrimaryKey
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val poster_path: String?,
    val popularity: Float?,
    val vote_average: Float?
)
