package ru.maribobah.academyhomework.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import ru.maribobah.academyhomework.data.Repository
import ru.maribobah.academyhomework.data.models.MoviesListCategory
import javax.inject.Singleton

@Singleton
class UpdateDataWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    lateinit var repository: Repository

    override suspend fun doWork(): Result {
        return try {
            enumValues<MoviesListCategory>().forEach { category ->
                val movies = repository.getMovies(category, Repository.Type.NETWORK)
                repository.saveMovies(movies, category)
            }
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(TAG, "Failed ot upload movies", throwable)
            Result.failure()
        }
    }

    companion object {
        const val TAG = "UpdateDataWorker"
    }
}