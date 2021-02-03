package ru.maribobah.academyhomework.data

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maribobah.academyhomework.data.localdb.AppDatabase
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.ActorsByMovie
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.data.localdb.entity.MoviesByCategory
import ru.maribobah.academyhomework.data.localdb.toActorEntityList
import ru.maribobah.academyhomework.data.localdb.toMovieEntityList
import ru.maribobah.academyhomework.data.tmdb.ImagesConverter
import ru.maribobah.academyhomework.data.tmdb.TmdbApi
import ru.maribobah.academyhomework.data.models.MoviesListCategory

class Repository(context: Context) {

    private val tmdbApi = TmdbApi.getInstance()
    private val localDb = AppDatabase.getInstance(context)
    private val imagesConverter = ImagesConverter()

    enum class Type {
        DB,
        NETWORK
    }

    suspend fun getMovies(category: MoviesListCategory, type: Type): List<MovieEntity> =
        withContext(Dispatchers.IO) {
            when (type) {
                Type.DB -> {
                    localDb.dao.getMovies(category.name).flatMap { it.movies }
                }
                Type.NETWORK -> {
                    val service = when (category) {
                        MoviesListCategory.NOW_PLAYING -> tmdbApi.services::popularMovies
                        MoviesListCategory.UPCOMING -> tmdbApi.services::upcomingMovies
                        MoviesListCategory.POPULAR -> tmdbApi.services::popularMovies
                        MoviesListCategory.TOP_RATED -> tmdbApi.services::topRatedMovies
                    }
                    val res = service.invoke()
                    res.movies.toMovieEntityList(imagesConverter)
                }
            }
        }

    suspend fun getMovieDetails(id: Long): MovieEntity = withContext(Dispatchers.IO) {
        localDb.dao.getMovieById(id)
    }

    suspend fun getMovieActors(id: Long, type: Type): List<ActorEntity> =
        withContext(Dispatchers.IO) {
            when (type) {
                Type.DB -> {
                    localDb.dao.getMovieActors(id).flatMap { it.actors }
                }
                Type.NETWORK -> {
                    val credits = tmdbApi.services.movieCredits(id)
                    credits.actors.toActorEntityList(imagesConverter)
                }
            }
        }

    suspend fun saveMovies(movies: List<MovieEntity>, category: MoviesListCategory) =
        withContext(Dispatchers.IO) {
            localDb.dao.deleteMoviesByCategory(category.name)
            movies.forEach { movie ->
                val id = localDb.dao.createMovieIfNotExist(movie)
                if (id == (-1).toLong()) {
                    localDb.dao.updateMovie(movie)
                }
                localDb.dao.addMovieToCategory(
                    MoviesByCategory(
                        category = category.name,
                        movie = movie.id
                    )
                )
            }
        }

    suspend fun saveActors(actors: List<ActorEntity>, movieId: Long) =
        withContext(Dispatchers.IO) {
            localDb.dao.deleteActorsByMovie(movieId)
            actors.forEach { actor ->
                val id = localDb.dao.createActorIfNotExist(actor)
                if (id == (-1).toLong()) {
                    localDb.dao.updateActor(actor)
                }
                localDb.dao.addActorToMovie(
                    ActorsByMovie(
                        movie = movieId,
                        actor = actor.id
                    )
                )
            }
        }
}