package ru.maribobah.academyhomework.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maribobah.academyhomework.data.localdb.Dao
import ru.maribobah.academyhomework.data.localdb.DataMapper
import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.ActorsByMovieEntity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.data.localdb.entity.MoviesByCategoryEntity
import ru.maribobah.academyhomework.data.tmdb.TmdbApi
import ru.maribobah.academyhomework.data.models.MoviesListCategory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dao: Dao,
    private val tmdbServices: TmdbApi.ServicesApi,
    private val dataMapper: DataMapper
) {

    enum class Type {
        DB,
        NETWORK
    }

    suspend fun getMovies(category: MoviesListCategory, type: Type): List<MovieEntity> =
        withContext(Dispatchers.IO) {
            when (type) {
                Type.DB -> {
                    dao.getMovies(category.name).flatMap { it.movies }
                }
                Type.NETWORK -> {
                    val service = when (category) {
                        MoviesListCategory.NOW_PLAYING -> tmdbServices::popularMovies
                        MoviesListCategory.UPCOMING -> tmdbServices::upcomingMovies
                        MoviesListCategory.POPULAR -> tmdbServices::popularMovies
                        MoviesListCategory.TOP_RATED -> tmdbServices::topRatedMovies
                    }
                    val res = service.invoke()
                    dataMapper.toMovieEntityList(res.movies)
                }
            }
        }

    suspend fun getMovieDetails(id: Long): MovieEntity? = withContext(Dispatchers.IO) {
        dao.getMovieById(id)
    }

    suspend fun getMovieActors(id: Long, type: Type): List<ActorEntity> =
        withContext(Dispatchers.IO) {
            when (type) {
                Type.DB -> {
                    dao.getMovieActors(id).flatMap { it.actors }
                }
                Type.NETWORK -> {
                    val credits = tmdbServices.movieCredits(id)
                    dataMapper.toActorEntityList(credits.actors)
                }
            }
        }

    suspend fun saveMovies(movies: List<MovieEntity>, category: MoviesListCategory) =
        withContext(Dispatchers.IO) {
            dao.deleteMoviesByCategory(category.name)
            movies.forEach { movie ->
                val id = dao.createMovieIfNotExist(movie)
                if (id == (-1).toLong()) {
                    dao.updateMovie(movie)
                }
                dao.addMovieToCategory(
                    MoviesByCategoryEntity(
                        category = category.name,
                        movie = movie.id
                    )
                )
            }
        }

    suspend fun saveActors(actors: List<ActorEntity>, movieId: Long) =
        withContext(Dispatchers.IO) {
            dao.deleteActorsByMovie(movieId)
            actors.forEach { actor ->
                val id = dao.createActorIfNotExist(actor)
                if (id == (-1).toLong()) {
                    dao.updateActor(actor)
                }
                dao.addActorToMovie(
                    ActorsByMovieEntity(
                        movie = movieId,
                        actor = actor.id
                    )
                )
            }
        }
}