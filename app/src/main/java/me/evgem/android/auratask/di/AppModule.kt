package me.evgem.android.auratask.di

import android.content.Context
import org.koin.dsl.module

val appModule = module {
    single {
        get<Context>().run { getSharedPreferences(packageName, Context.MODE_PRIVATE) }
    }
}
