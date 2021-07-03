package com.prueba.pruebanapoleon.di.component

import android.app.Application
import android.content.Context
import com.prueba.pruebanapoleon.application.MyApplication
import com.prueba.pruebanapoleon.di.ApplicationContext
import com.prueba.pruebanapoleon.di.DatabaseInfo
import com.prueba.pruebanapoleon.di.module.ApplicationModule
import com.prueba.pruebanapoleon.di.module.DatabaseModule
import com.prueba.pruebanapoleon.di.module.RetrofitModule
import com.prueba.pruebanapoleon.view.detail.DetailActivity
import com.prueba.pruebanapoleon.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun inject(myApplication: MyApplication?)
    fun inject(mainActivity: MainActivity?)
    fun inject(detailActivity: DetailActivity?)

    @get:ApplicationContext
    val context: Context?
    val application: Application?

    @get:DatabaseInfo
    val databaseName: String?
}