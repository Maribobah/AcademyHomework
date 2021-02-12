package ru.maribobah.academyhomework.data.localdb

import androidx.room.*
import androidx.room.Dao
import ru.maribobah.academyhomework.data.localdb.entity.*

@Dao
interface Dao {

    @Transaction
    @Query("""
            SELECT * FROM movies_by_category 
            WHERE category = :category
            ORDER BY _id ASC""")
    suspend fun getMovies(category: String) : List<CategoryWithMoviesEntity>

    @Query("""
        SELECT * FROM movies 
        WHERE _id = :id """)
    suspend fun getMovieById(id: Long) : MovieEntity?

    @Transaction
    @Query("""
        SELECT * FROM actors_by_movie
        WHERE movie = :movie
        ORDER BY _id ASC""")
    suspend fun getMovieActors(movie: Long) : List<MovieWithActorsEntity>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createMovieIfNotExist(movie: MovieEntity) : Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovieToCategory(record: MoviesByCategoryEntity)

    @Query("""
        DELETE FROM movies_by_category
        WHERE category = :category
    """)
    suspend fun deleteMoviesByCategory(category: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createActorIfNotExist(actor: ActorEntity): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateActor(actor: ActorEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActorToMovie(record: ActorsByMovieEntity)

    @Query("""
        DELETE FROM actors_by_movie
        WHERE movie = :movie
    """)
    suspend fun deleteActorsByMovie(movie: Long)

}