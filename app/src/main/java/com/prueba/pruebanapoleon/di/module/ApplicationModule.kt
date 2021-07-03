package com.prueba.pruebanapoleon.di.module

import android.app.Application
import android.content.Context
import com.prueba.pruebanapoleon.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(@ApplicationContext val application: Application) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }
}