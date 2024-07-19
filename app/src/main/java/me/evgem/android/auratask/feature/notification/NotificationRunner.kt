package me.evgem.android.auratask.feature.notification

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import me.evgem.android.auratask.domain.repository.NotificationRepository
import java.util.concurrent.TimeUnit

class NotificationRunner(
    private val notificationRepository: NotificationRepository,
    private val context: Context,
) {

    companion object {
        private const val NAME_15_MINUTE_RULE = "15_MINUTE_RULE"
        private const val NAME_DISMISS_RULE = "DISMISS_RULE"
        private const val DISMISSAL_LIMIT = 5
    }

    fun run() {
        val is15MinRule = notificationRepository.dismissalCount % DISMISSAL_LIMIT == 0
        val workManager = WorkManager.getInstance(context)
        if (is15MinRule) {
            val request = PeriodicWorkRequestBuilder<NotificationWorker>(15, TimeUnit.MINUTES)
                .build()
            workManager.enqueueUniquePeriodicWork(
                NAME_15_MINUTE_RULE,
                ExistingPeriodicWorkPolicy.KEEP,
                request,
            )
            workManager.cancelUniqueWork(NAME_DISMISS_RULE)
        } else {
            val durationMinutes = notificationRepository.run {
                dismissalCount % DISMISSAL_LIMIT * 20L
            }
            val request = OneTimeWorkRequestBuilder<NotificationWorker>()
                .setInitialDelay(durationMinutes, TimeUnit.MINUTES)
                .build()
            workManager.enqueueUniqueWork(
                NAME_DISMISS_RULE,
                ExistingWorkPolicy.REPLACE,
                request,
            )
            workManager.cancelUniqueWork(NAME_15_MINUTE_RULE)
        }
    }
}
