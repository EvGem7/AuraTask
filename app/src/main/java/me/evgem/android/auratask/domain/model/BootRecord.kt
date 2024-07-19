package me.evgem.android.auratask.domain.model

import java.time.ZonedDateTime

data class BootRecord(
    val id: Long,
    val timestamp: ZonedDateTime,
)
