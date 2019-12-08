package com.cts.canada.dependencyinjection.component

import com.cts.canada.dependencyinjection.module.ActivityModule
import com.cts.canada.view.FactsActivity
import dagger.Component

@Component(dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: FactsActivity)


}