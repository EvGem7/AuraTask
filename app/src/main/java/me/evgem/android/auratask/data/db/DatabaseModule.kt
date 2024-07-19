package me.evgem.android.auratask.data.db

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.NAME).build()
    }
    factory { get<AppDatabase>().bootDao() }
}
