package me.evgem.android.auratask.domain.repository

interface NotificationRepository {

    var dismissalCount: Int
    var totalDismissalsAllowed: Int
    var intervalBetweenDismissalsMinutes: Int
}
