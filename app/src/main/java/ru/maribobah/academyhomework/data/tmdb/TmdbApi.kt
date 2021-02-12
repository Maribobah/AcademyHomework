package ru.maribobah.academyhomework.data.tmdb

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import ru.maribobah.academyhomework.data.models.*

class TmdbApi {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(ApiKeyInterceptor())
        .build()

    @ExperimentalSerializationApi
    private val retrofit: Retrofit = kotlin.run {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    interface ServicesApi {
        @GET("configuration")
        suspend fun configuration(): ConfigurationInfo

        @GET("movie/popular")
        suspend fun popularMovies(): MoviesListResponse

        @GET("movie/now_playing")
        suspend fun nowPlayingMovies(): MoviesListResponse

        @GET("movie/top_rated")
        suspend fun topRatedMovies(): MoviesListResponse

        @GET("movie/upcoming")
        suspend fun upcomingMovies(): MoviesListResponse

        @GET("movie/{movie_id}")
        suspend fun movieDetails(@Path("movie_id") movieId: Int): Movie

        @GET("movie/{movie_id}/credits")
        suspend fun movieCredits(@Path("movie_id") movieId: Long): MovieCreditsResponse

        @GET("movie/{person_id}")
        suspend fun personDetails(@Path("person_id") personId: Int): Actor
    }

    fun services() : ServicesApi = retrofit.create(ServicesApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}
