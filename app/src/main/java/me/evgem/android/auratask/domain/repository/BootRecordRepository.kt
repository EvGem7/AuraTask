package me.evgem.android.auratask.domain.repository

import me.evgem.android.auratask.domain.model.BootRecord

interface BootRecordRepository {

    suspend fun logBoot(timestamp: Long)

    suspend fun getLastTwoRecords(): List<BootRecord>
}
