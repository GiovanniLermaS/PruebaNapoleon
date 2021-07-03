package com.prueba.pruebanapoleon.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Post(
        @SerializedName("userId")
        var userId: Int,
        @PrimaryKey @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("body")
        var body: String,
        var isColor: Boolean,
        var isFavorite: Boolean = false
) : Serializable