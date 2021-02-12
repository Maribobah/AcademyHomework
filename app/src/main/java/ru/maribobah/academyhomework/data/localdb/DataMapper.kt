package ru.maribobah.academyhomework.data.localdb

import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie
import ru.maribobah.academyhomework.data.tmdb.ImagesConverter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataMapper @Inject constructor(
    private val imagesConverter: ImagesConverter
) {
    suspend fun toMovieEntity(movie: Movie) = MovieEntity(
        id = movie.id.toLong(),
        name = movie.name,
        rate = if (movie.adult) ADULT_RATE else PG_RATE,
        like = movie.like,
        stars = movie.stars / 2,
        reviews = "${movie.votesCount} $REVIEWS",
        duration = if (movie.runtime == 0) "" else "${movie.runtime} $RUNTIME_UNIT",
        poster = imagesConverter.getFullPosterPath(movie.poster),
        backdrop = imagesConverter.getFullBackdropPath(movie.backdrop),
        storyline = movie.storyline,
        genres = movie.genres.joinToString { genre ->  genre.name }
    )

    suspend fun toMovieEntityList(movieList: List<Movie>) =
        movieList.map { movie ->  toMovieEntity(movie) }

    suspend fun toActorEntity(actor: Actor) = ActorEntity(
        id = actor.id.toLong(),
        name = actor.name,
        avatar = imagesConverter.getFullProfilePath(actor.avatar)
    )

    suspend fun toActorEntityList(actorList: List<Actor>) =
        actorList.map { actor ->  toActorEntity(actor) }

    companion object {
        private const val ADULT_RATE = "16+"
        private const val PG_RATE = "13+"
        private const val REVIEWS = "reviews"
        private const val RUNTIME_UNIT = "MIN"
    }

}