package br.com.pedro.moviesm2u.data.remote

import br.com.pedro.moviesm2u.data.LocalData
import br.com.pedro.moviesm2u.data.entities.Movie
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
    ): Response<List<Movie>>

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = LocalData().API_KEY,
        @Query("language") language : String = LocalData().language,
        @Query("region") region : String = LocalData().region
    ): Response<Movie>
}