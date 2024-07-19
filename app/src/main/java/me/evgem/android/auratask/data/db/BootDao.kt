package me.evgem.android.auratask.data.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface BootDao {

    @Insert
    suspend fun insert(entity: BootEntity)
}
