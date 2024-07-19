package me.evgem.android.auratask.presentation

import android.content.Context
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import me.evgem.android.auratask.R

class NotificationHelper(private val context: Context) {

    companion object {
        private const val DEFAULT_CHANNEL_ID = "default_channel"
    }

    fun getDefaultChannelId(): String {
        val channel = NotificationChannelCompat.Builder(
            DEFAULT_CHANNEL_ID,
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
        )
            .setName(context.getString(R.string.default_notification_channel_name))
            .build()
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
        return DEFAULT_CHANNEL_ID
    }
}
