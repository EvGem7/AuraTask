package me.evgem.android.auratask.feature.boot_monitor

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import me.evgem.android.auratask.domain.repository.BootRepository

class BootMonitorWorker(
    appContext: Context,
    params: WorkerParameters,
    private val bootRepository: BootRepository,
) : CoroutineWorker(appContext, params) {

    companion object {
        const val KEY_TIMESTAMP = "KEY_TIMESTAMP"
    }

    override suspend fun doWork(): Result {
        val timestamp = inputData.getLong(KEY_TIMESTAMP, System.currentTimeMillis())
        bootRepository.logBoot(timestamp)
        return Result.success()
    }
}