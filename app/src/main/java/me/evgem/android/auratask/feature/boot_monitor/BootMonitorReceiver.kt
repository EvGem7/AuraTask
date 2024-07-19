package me.evgem.android.auratask.feature.boot_monitor

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import androidx.work.workDataOf

class BootMonitorReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Intent.ACTION_BOOT_COMPLETED) {
            return
        }
        val request = OneTimeWorkRequestBuilder<BootMonitorWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setInputData(workDataOf(BootMonitorWorker.KEY_TIMESTAMP to System.currentTimeMillis()))
            .build()
        WorkManager.getInstance(context).enqueue(request)
    }
}
