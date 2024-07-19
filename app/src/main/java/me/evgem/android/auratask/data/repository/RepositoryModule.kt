package me.evgem.android.auratask.data.repository

import me.evgem.android.auratask.domain.repository.BootRecordRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    factoryOf(::DefaultBootRecordRepository) bind BootRecordRepository::class
}
