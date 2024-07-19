package me.evgem.android.auratask.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BootEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
)
