package com.cts.canada.dependencyinjection.component


import com.cts.canada.FactsApplication
import com.cts.canada.dependencyinjection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by lucasrichards on 27/09/2017.
 */

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: FactsApplication)
}