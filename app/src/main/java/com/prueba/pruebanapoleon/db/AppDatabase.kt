package com.prueba.pruebanapoleon.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prueba.pruebanapoleon.db.dao.PostDao
import com.prueba.pruebanapoleon.db.model.Post

@Database(
        entities = [Post::class],
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): PostDao
}