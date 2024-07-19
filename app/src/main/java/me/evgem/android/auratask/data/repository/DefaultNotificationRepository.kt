package me.evgem.android.auratask.data.repository

import android.content.SharedPreferences
import me.evgem.android.auratask.data.util.int
import me.evgem.android.auratask.domain.repository.NotificationRepository

class DefaultNotificationRepository(sharedPreferences: SharedPreferences) : NotificationRepository {

    override var dismissalCount: Int by sharedPreferences.int()
    override var totalDismissalsAllowed: Int by sharedPreferences.int(defaultValue = 5)
    override var intervalBetweenDismissalsMinutes: Int by sharedPreferences.int(defaultValue = 20)
}
