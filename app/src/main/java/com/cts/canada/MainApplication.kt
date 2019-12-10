package com.cts.canada

import android.app.Application
import com.cts.canada.dependencyinjection.component.AppComponent

import com.cts.canada.dependencyinjection.component.DaggerAppComponent
import com.cts.canada.dependencyinjection.module.RxThreadModule


open class MainApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        createComponent()
    }

    protected open fun createComponent() {
        component = DaggerAppComponent.builder()
                .rxThreadModule(RxThreadModule(this))

                     .build()
    }
}

