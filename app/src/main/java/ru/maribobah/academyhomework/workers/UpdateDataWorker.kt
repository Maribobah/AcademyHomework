package ru.maribobah.academyhomework.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UpdateDataWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}