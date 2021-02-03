package ru.maribobah.academyhomework.data.localdb

import androidx.room.*
import androidx.room.Dao
import ru.maribobah.academyhomework.data.localdb.entity.*

@Dao
interface Dao {

    @Query("""
            SELECT * FROM ${DbContract.MoviesByCategory.TABLE_NAME} 
            WHERE ${DbContract.MoviesByCategory.COLUMN_NAME_CATEGORY} = :category
            ORDER BY ${DbContract.MoviesByCategory.COLUMN_NAME_ID} ASC""")
    suspend fun getMovies(category: String) : List<CategoryWithMovies>

    @Query("""
        SELECT * FROM ${DbContract.Movies.TABLE_NAME} 
        WHERE ${DbContract.Movies.COLUMN_NAME_ID} = :id """)
    suspend fun getMovieById(id: Long) : MovieEntity

    @Query("""
        SELECT * FROM ${DbContract.ActorsByMovie.TABLE_NAME}
        WHERE ${DbContract.ActorsByMovie.COLUMN_NAME_MOVIE} = :movie
        ORDER BY ${DbContract.ActorsByMovie.COLUMN_NAME_ID} ASC""")
    suspend fun getMovieActors(movie: Long) : List<MovieWithActors>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createMovieIfNotExist(movie: MovieEntity) : Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovieToCategory(record: MoviesByCategory)

    @Query("""
        DELETE FROM ${DbContract.MoviesByCategory.TABLE_NAME}
        WHERE ${DbContract.MoviesByCategory.COLUMN_NAME_CATEGORY} = :category
    """)
    suspend fun deleteMoviesByCategory(category: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createActorIfNotExist(actor: ActorEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActorToMovie(record: ActorsByMovie)

    @Query("""
        DELETE FROM ${DbContract.ActorsByMovie.TABLE_NAME}
        WHERE ${DbContract.ActorsByMovie.COLUMN_NAME_MOVIE} = :movie
    """)
    suspend fun deleteActorsByMovie(movie: Long)

}