package me.evgem.android.auratask.presentation

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {
    factoryOf(::NotificationHelper)
}
