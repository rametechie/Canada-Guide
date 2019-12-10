package com.cts.canada.dependencyinjection.component

import com.cts.canada.dependencyinjection.module.RxThreadModule
import com.cts.canada.view.FactsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(RxThreadModule::class))
interface AppComponent {
    fun inject(factsActivity: FactsActivity)
}