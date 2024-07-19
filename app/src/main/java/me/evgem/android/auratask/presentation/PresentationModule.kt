package me.evgem.android.auratask.presentation

import me.evgem.android.auratask.feature.notification.NotificationRunner
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::NotificationHelper)
    factoryOf(::NotificationRunner)
}
