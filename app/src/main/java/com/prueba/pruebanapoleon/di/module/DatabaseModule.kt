package com.prueba.pruebanapoleon.di.module

import android.content.Context
import androidx.room.Room
import com.prueba.pruebanapoleon.db.AppDatabase
import com.prueba.pruebanapoleon.di.ApplicationContext
import com.prueba.pruebanapoleon.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(@ApplicationContext val context: Context) {
    @DatabaseInfo
    private val dbName = "db_app"

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                dbName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return dbName
    }
}