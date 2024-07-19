package me.evgem.android.auratask.di

import me.evgem.android.auratask.feature.boot_monitor.BootMonitorWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val workManagerModule = module {
    workerOf(::BootMonitorWorker)
}
