package com.cts.canada.dependencyinjection.module

import android.app.Application
import com.cts.canada.dependencyinjection.ApplicationContext
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
open class RxThreadModule(private val application: Application) {

    companion object {
        const val mainThread = "mainThread"
        const val ioThread = "ioThread"
            }

    @Singleton
    @Provides
    @Named(mainThread)
    open fun provideAndroidSchedulers(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @Named(ioThread)
    open fun provideSchedulersIO(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @ApplicationContext
    open fun provideContext(): Application = this.application
}