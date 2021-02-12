package ru.maribobah.academyhomework.workers

import android.content.Context
import androidx.work.*
import ru.maribobah.academyhomework.data.Repository

class UpdateDataWorkerFactory(private val repository: Repository) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        val workerClass = Class.forName(workerClassName).asSubclass(CoroutineWorker::class.java)
        val constructor = workerClass.getDeclaredConstructor(Context::class.java, WorkerParameters::class.java)
        val instance = constructor.newInstance(appContext, workerParameters)

        when (instance) {
            is UpdateDataWorker -> instance.repository = repository
            else -> throw IllegalArgumentException("$workerClassName is not registered Worker")
        }
        return instance
    }
}