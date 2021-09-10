package br.com.pedro.moviesm2u.di

import br.com.pedro.moviesm2u.data.local.AppDatabase
import br.com.pedro.moviesm2u.data.local.MovieDao
import br.com.pedro.moviesm2u.data.remote.MovieRemoteDataSource
import br.com.pedro.moviesm2u.data.remote.MovieService
import br.com.pedro.moviesm2u.data.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService) = MovieRemoteDataSource(movieService)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieDao
    ) =
        MovieRepository(remoteDataSource, localDataSource)
}