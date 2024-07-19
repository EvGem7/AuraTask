package me.evgem.android.auratask.feature.boot_monitor

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import me.evgem.android.auratask.domain.repository.BootRecordRepository
import me.evgem.android.auratask.feature.notification.NotificationWorker
import java.util.concurrent.TimeUnit

class BootMonitorWorker(
    appContext: Context,
    params: WorkerParameters,
    private val bootRecordRepository: BootRecordRepository,
) : CoroutineWorker(appContext, params) {

    companion object {
        const val KEY_TIMESTAMP = "KEY_TIMESTAMP"
    }

    override suspend fun doWork(): Result {
        val timestamp = inputData.getLong(KEY_TIMESTAMP, System.currentTimeMillis())
        bootRecordRepository.logBoot(timestamp)
        startNotificationWorker()
        return Result.success()
    }

    private fun startNotificationWorker() {
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES).build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }
}