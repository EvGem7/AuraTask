package me.evgem.android.auratask.feature.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import me.evgem.android.auratask.domain.repository.NotificationRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NotificationDismissedReceiver : BroadcastReceiver(), KoinComponent {

    private val notificationRunner: NotificationRunner by inject()
    private val notificationRepository: NotificationRepository by inject()

    override fun onReceive(context: Context, intent: Intent) {
        notificationRepository.dismissalCount++
        notificationRunner.run()
    }
}
