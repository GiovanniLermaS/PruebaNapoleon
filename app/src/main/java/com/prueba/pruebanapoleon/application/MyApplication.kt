package com.prueba.pruebanapoleon.application

import android.app.Application
import com.prueba.pruebanapoleon.di.component.ApplicationComponent
import com.prueba.pruebanapoleon.di.component.DaggerApplicationComponent
import com.prueba.pruebanapoleon.di.module.ApplicationModule
import com.prueba.pruebanapoleon.di.module.DatabaseModule
import com.prueba.pruebanapoleon.di.module.RetrofitModule

class MyApplication : Application() {

    var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .retrofitModule(RetrofitModule(this))
                .databaseModule(DatabaseModule(this))
                .build()
        mApplicationComponent?.inject(this)
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}