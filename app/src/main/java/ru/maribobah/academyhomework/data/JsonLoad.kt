import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.maribobah.academyhomework.data.models.Actor
import ru.maribobah.academyhomework.data.models.Genre
import ru.maribobah.academyhomework.data.models.Movie
import java.lang.IllegalArgumentException

private val jsonFormat = Json { ignoreUnknownKeys = true }

@Serializable
private class JsonMovie(
    val id: Int,
    val title: String,
    @SerialName("poster_path")
    val poster: String,
    @SerialName("backdrop_path")
    val backdrop: String,
    val runtime: Int,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val actors: List<Int>,
    @SerialName("vote_average")
    val ratings: Float,
    @SerialName("vote_count")
    val reviews: Int,
    val overview: String,
    val adult: Boolean
)

internal suspend fun loadMoviesFromAssets(context: Context): List<Movie> =
    withContext(Dispatchers.IO) {
        val genresMap = loadGenres(context)
        val actorsMap = loadActors(context)

        val data = readAssetFileToString(context, "data.json")
        parseMovies(data, genresMap, actorsMap)
    }

internal suspend fun findMovieInAssets(context: Context, id: Int): Movie =
    withContext(Dispatchers.IO) {
        val genresMap = loadGenres(context)
        val actorsMap = loadActors(context)

        val data = readAssetFileToString(context, "data.json")
        findMovie(data, genresMap, actorsMap, id)
    }

internal fun parseMovies(data: String, genres: List<Genre>, actors: List<Actor>): List<Movie> {
    val genresMap = genres.associateBy { it.id }
    val actorsMap = actors.associateBy { it.id }
    val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(data)
    return jsonMovies.map { createMovie(it, genresMap, actorsMap) }
}

internal fun findMovie(data: String, genres: List<Genre>, actors: List<Actor>, id: Int): Movie {
    val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(data).asSequence()
    val dataMovie = jsonMovies.first { it.id == id }
    val genresMap = genres.associateBy { it.id }
    val actorsMap = actors.associateBy { it.id }
    return createMovie(dataMovie, genresMap, actorsMap)
}

private fun createMovie(jsonMovie: JsonMovie, genres: Map<Int, Genre>, actors: Map<Int, Actor>): Movie {
   return Movie(
        id = jsonMovie.id,
        name = jsonMovie.title,
        storyline = jsonMovie.overview,
        poster = jsonMovie.poster,
        backdrop = jsonMovie.backdrop,
        stars = jsonMovie.ratings,
        reviews = jsonMovie.reviews,
        rate = if (jsonMovie.adult) "16+" else "13+",
        duration = jsonMovie.runtime,
        genres = jsonMovie.genreIds.map {
            genres[it] ?: throw IllegalArgumentException("Genre not found")
        },
        actors = jsonMovie.actors.map {
            actors[it] ?: throw IllegalArgumentException("Actor not found")
        }
    )
}

private suspend fun loadGenres(context: Context): List<Genre> = withContext(Dispatchers.IO) {
    val data = readAssetFileToString(context, "genres.json")
    jsonFormat.decodeFromString<List<Genre>>(data)
}

private suspend fun loadActors(context: Context): List<Actor> = withContext(Dispatchers.IO) {
    val data = readAssetFileToString(context, "people.json")
    jsonFormat.decodeFromString<List<Actor>>(data)
}

private fun readAssetFileToString(context: Context, fileName: String): String {
    val stream = context.assets.open(fileName)
    return stream.bufferedReader().readText()
}