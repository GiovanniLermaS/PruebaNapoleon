package com.prueba.pruebanapoleon.db.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("phone")
    var phone: String? = null
}