package me.evgem.android.auratask.data.repository

import android.content.SharedPreferences
import me.evgem.android.auratask.data.util.int
import me.evgem.android.auratask.domain.repository.NotificationRepository

class DefaultNotificationRepository(sharedPreferences: SharedPreferences) : NotificationRepository {

    override var dismissalCount: Int by sharedPreferences.int()
}
