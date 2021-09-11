package br.com.pedro.moviesm2u.data.remote

import br.com.pedro.moviesm2u.data.LocalData
import br.com.pedro.moviesm2u.data.entities.Movie
import br.com.pedro.moviesm2u.data.entities.MovieList
import br.com.pedro.moviesm2u.data.entities.MoviesSimilar
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") apiKey: String = LocalData().API_KEY,
        @Query("language") language : String = LocalData().language,
        @Query("region") region : String = LocalData().region
    ): Response<MovieList>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = LocalData().API_KEY,
        @Query("language") language : String = LocalData().language,
        @Query("region") region : String = LocalData().region
    ): Response<Movie>

    @GET("/movie/{id}/similar")
    suspend fun getMoviesSimilar(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = LocalData().API_KEY,
        @Query("language") language : String = LocalData().language,
        @Query("region") region : String = LocalData().region
    ) : Response<MoviesSimilar>

}