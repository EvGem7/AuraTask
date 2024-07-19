package me.evgem.android.auratask

import android.app.Application
import me.evgem.android.auratask.data.db.databaseModule
import me.evgem.android.auratask.data.repository.repositoryModule
import me.evgem.android.auratask.di.workManagerModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                databaseModule,
                workManagerModule,
                repositoryModule,
            )
            workManagerFactory()
        }
    }
}
