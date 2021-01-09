package com.shaadi.assignment.di.module

import androidx.room.Room
import com.shaadi.assignment.BuildConfig
import com.shaadi.assignment.ShaadiApplication
import com.shaadi.assignment.data.local.db.DatabaseService
import com.shaadi.assignment.data.remote.NetworkService
import com.shaadi.assignment.data.remote.Networking
import com.shaadi.assignment.utils.NetworkHelper
import com.shaadi.assignment.utils.RxSchedulerProvider
import com.shaadi.assignment.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(val application: ShaadiApplication) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "shaadi-db"
        ).build()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

}