package me.evgem.android.auratask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BootEntity::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val NAME = "default-db"
    }

    abstract fun bootDao(): BootDao
}
