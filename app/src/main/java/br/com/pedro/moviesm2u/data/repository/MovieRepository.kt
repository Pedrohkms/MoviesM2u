package br.com.pedro.moviesm2u.data.repository

import br.com.pedro.moviesm2u.data.local.MovieDao
import br.com.pedro.moviesm2u.data.remote.MovieRemoteDataSource
import br.com.pedro.moviesm2u.utils.performGetOperation
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieDao
) {

    fun getMovie(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getMovie(id) },
        networkCall = { remoteDataSource.getMovie(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getMovies() = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getMoviesFav() = performGetOperation(
        databaseQuery = { localDataSource.getMoviesFav() },
        networkCall = { remoteDataSource.getMovies() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

//    fun getMoviesFav() = localDataSource.getMoviesFav()
}