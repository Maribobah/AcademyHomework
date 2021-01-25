package ru.maribobah.academyhomework.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Movie
import ru.maribobah.academyhomework.data.tmdb.ImagesConverter
import ru.maribobah.academyhomework.data.tmdb.MovieConverter
import ru.maribobah.academyhomework.data.tmdb.TmdbApi

class Repository {

    private val tmdbApi = TmdbApi.getInstance()
    private val imagesConverter = ImagesConverter()

    suspend fun getPopularMovies() : List<Movie> = withContext(Dispatchers.IO) {
        val res = tmdbApi.services.popularMovies()
        val movies = res.movies.map { movie ->
            convertMovieDetails(movie)
            movie
        }
        movies
    }

    suspend fun getMovieDetails(id: Int) : Movie = withContext(Dispatchers.IO) {
        val movieDetails = tmdbApi.services.movieDetails(id)
        convertMovieDetails(movieDetails)
        movieDetails
    }

    suspend fun getMovieActors(id: Int) : List<Actor> = withContext(Dispatchers.IO) {
        val credits = tmdbApi.services.movieCredits(id)
        return@withContext credits.actors.map { actor ->
            if (actor.avatar != null) {
                actor.avatar = imagesConverter.getFullProfilePath(actor.avatar)
            }
            actor
        }
    }

    private suspend fun convertMovieDetails(movie: Movie) {
        movie.backdrop = imagesConverter.getFullBackdropPath(movie.backdrop)
        movie.poster = imagesConverter.getFullPosterPath(movie.poster)
        movie.reviews = MovieConverter.fromVoteCountToReviews(movie.votesCount)
        movie.duration = MovieConverter.fromRuntimeToDuration(movie.runtime)
        movie.genresPresentation = MovieConverter.genresPresentation(movie.genres)
        movie.stars = MovieConverter.starsFormat(movie.stars)
        movie.rate = MovieConverter.fromAdultToRate(movie.adult)
    }

}