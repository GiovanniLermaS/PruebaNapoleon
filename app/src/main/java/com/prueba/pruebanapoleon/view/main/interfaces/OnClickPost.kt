package com.prueba.pruebanapoleon.view.main.interfaces

import com.prueba.pruebanapoleon.db.model.Post

interface OnClickPost {
    fun goDetailActivity(post: Post)
    fun addFavorites(post: Post)
}