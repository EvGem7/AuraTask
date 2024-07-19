package me.evgem.android.auratask.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootRecordDao {

    @Insert
    suspend fun insert(entity: BootRecordEntity)

    @Query("SELECT * FROM BootRecordEntity ORDER BY timestamp DESC LIMIT 2")
    suspend fun getLastTwoRecords(): List<BootRecordEntity>

    @Query("SELECT * FROM BootRecordEntity ORDER BY timestamp")
    suspend fun getAllRecords(): List<BootRecordEntity>
}
