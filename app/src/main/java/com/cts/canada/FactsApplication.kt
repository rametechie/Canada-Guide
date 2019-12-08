package com.cts.canada

import android.app.Application
import com.cts.canada.dependencyinjection.component.ApplicationComponent
import com.cts.canada.dependencyinjection.module.ApplicationModule
import com.cts.canada.dependencyinjection.component.DaggerApplicationComponent


class FactsApplication : Application() {


    companion object {
        lateinit var applicationComponent: ApplicationComponent
        var isDeviceRooted: Boolean = false
    }


    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        applicationComponent.inject(this)

    }

}