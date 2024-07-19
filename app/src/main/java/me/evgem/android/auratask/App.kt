package me.evgem.android.auratask

import android.app.Application
import me.evgem.android.auratask.data.db.databaseModule
import me.evgem.android.auratask.data.repository.repositoryModule
import me.evgem.android.auratask.di.workManagerModule
import me.evgem.android.auratask.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                workManagerModule,
                repositoryModule,
                presentationModule,
            )
            workManagerFactory()
        }
    }
}
