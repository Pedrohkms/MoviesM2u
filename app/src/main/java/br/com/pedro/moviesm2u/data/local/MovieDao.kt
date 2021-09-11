package br.com.pedro.moviesm2u.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.pedro.moviesm2u.data.entities.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie ")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movie WHERE isFavorited = 1")
    fun getMoviesFav() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movies: Movie)

    @Query("UPDATE movie SET isFavorited = :isFav WHERE id = :id")
    suspend fun updateFav(isFav : Boolean, id: Int)
}