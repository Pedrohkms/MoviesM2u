package br.com.pedro.moviesm2u.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie (
    @PrimaryKey
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val poster_path: String?,
    val popularity: Float?,
    val vote_average: Float?,
    var isFavorited: Boolean
)