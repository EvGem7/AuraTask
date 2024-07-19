package me.evgem.android.auratask.data.repository

import me.evgem.android.auratask.data.db.BootDao
import me.evgem.android.auratask.data.db.BootEntity
import me.evgem.android.auratask.domain.repository.BootRepository

class DefaultBootRepository(
    private val bootDao: BootDao,
) : BootRepository {

    override suspend fun logBoot(timestamp: Long) {
        bootDao.insert(BootEntity(timestamp = timestamp))
    }
}
