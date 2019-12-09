package com.cts.canada.dependencyinjection.module

import android.app.Application
import android.content.Context
import com.cts.canada.presenter.FactsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by lucasrichards on 27/09/2017.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun factsPresenter(context: Context) : FactsPresenter {
        return  FactsPresenter(context)
    }

}