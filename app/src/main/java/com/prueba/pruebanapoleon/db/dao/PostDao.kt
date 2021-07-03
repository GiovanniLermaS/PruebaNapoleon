package com.prueba.pruebanapoleon.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prueba.pruebanapoleon.db.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post WHERE id = :id")
    suspend fun getPostById(id: Int): Post

    @Query("SELECT * FROM Post")
    suspend fun getPosts(): List<Post>

    @Query("DELETE FROM Post WHERE id=:id")
    suspend fun deletePostById(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setPost(post: Post?): Long
}