package ru.maribobah.academyhomework.data.localdb

import ru.maribobah.academyhomework.data.localdb.entity.ActorEntity
import ru.maribobah.academyhomework.data.localdb.entity.MovieEntity
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie
import ru.maribobah.academyhomework.data.tmdb.ImagesConverter

suspend fun Movie.toMovieEntity(imagesConverter: ImagesConverter) = MovieEntity(
    id = id.toLong(),
    name = name,
    rate = if (adult) "16+" else "13+",
    like = like,
    stars = stars / 2,
    reviews = "$votesCount reviews",
    duration = if (runtime == 0) "" else "$runtime MIN",
    poster = imagesConverter.getFullPosterPath(poster),
    backdrop = imagesConverter.getFullBackdropPath(backdrop),
    storyline = storyline,
    genres = genres.joinToString { name }
)

suspend fun List<Movie>.toMovieEntityList(imagesConverter: ImagesConverter) =
    map { it.toMovieEntity(imagesConverter) }

suspend fun Actor.toActorEntity(imagesConverter: ImagesConverter) = ActorEntity(
    id = id.toLong(),
    name = name,
    avatar = imagesConverter.getFullProfilePath(avatar)
)

suspend fun List<Actor>.toActorEntityList(imagesConverter: ImagesConverter) =
    map { it.toActorEntity(imagesConverter) }