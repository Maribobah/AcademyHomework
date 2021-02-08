package ru.maribobah.academyhomework.workers

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

class WorkRepository {

    companion object {

        private val UPLOAD_DATA_DURATION: Long = 8
        val UPLOAD_DATA_TAG: String = "Upload Data"

        fun uploadData(): PeriodicWorkRequest =
            PeriodicWorkRequestBuilder<UpdateDataWorker>(
                UPLOAD_DATA_DURATION, TimeUnit.HOURS
            )
                .setConstraints(uploadDataConstraints())
                .addTag(UPLOAD_DATA_TAG)
                .build()

        private fun uploadDataConstraints(): Constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build()
    }
}