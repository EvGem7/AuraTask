package me.evgem.android.auratask.domain.repository

interface BootRepository {

    suspend fun logBoot(timestamp: Long)
}
