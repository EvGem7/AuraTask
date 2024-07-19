package me.evgem.android.auratask.data.repository

import me.evgem.android.auratask.data.db.BootRecordDao
import me.evgem.android.auratask.data.db.BootRecordEntity
import me.evgem.android.auratask.domain.model.BootRecord
import me.evgem.android.auratask.domain.repository.BootRecordRepository
import java.time.Instant
import java.time.ZoneId

class DefaultBootRecordRepository(
    private val bootRecordDao: BootRecordDao,
) : BootRecordRepository {

    override suspend fun logBoot(timestamp: Long) {
        bootRecordDao.insert(BootRecordEntity(timestamp = timestamp))
    }

    override suspend fun getLastTwoRecords(): List<BootRecord> {
        return bootRecordDao.getLastTwoRecords().map { it.toDomain() }
    }

    private fun BootRecordEntity.toDomain(): BootRecord {
        return BootRecord(
            id = id,
            timestamp = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()),
        )
    }
}
