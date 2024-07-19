package me.evgem.android.auratask.feature.notification

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import me.evgem.android.auratask.R
import me.evgem.android.auratask.domain.model.BootRecord
import me.evgem.android.auratask.domain.repository.BootRecordRepository
import me.evgem.android.auratask.presentation.NotificationHelper
import java.time.format.DateTimeFormatter
import kotlin.math.absoluteValue
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class NotificationWorker(
    appContext: Context,
    params: WorkerParameters,
    private val bootRecordRepository: BootRecordRepository,
    private val notificationHelper: NotificationHelper,
) : CoroutineWorker(appContext, params) {

    companion object {
        private const val NOTIFICATION_ID = 127
    }

    override suspend fun doWork(): Result {
        val context = applicationContext
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return Result.failure()
        }
        val content = getContent(bootRecordRepository.getLastTwoRecords())
        NotificationCompat.Builder(context, notificationHelper.getDefaultChannelId())
            .setContentText(content)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
            .let { NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, it) }
        return Result.success()
    }

    private fun getContent(bootRecords: List<BootRecord>): String {
        val context = applicationContext
        return when {
            bootRecords.isEmpty() -> context.getString(R.string.notification_no_records)

            bootRecords.size == 1 -> {
                val date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    .format(bootRecords.first().timestamp)
                context.getString(R.string.notification_one_record, date)
            }

            else -> {
                val delta = bootRecords[0].timestamp.toEpochSecond()
                    .minus(bootRecords[1].timestamp.toEpochSecond())
                    .absoluteValue
                    .toDuration(DurationUnit.SECONDS)
                    .toString()
                "Last boots time delta = $delta"
            }
        }
    }
}
