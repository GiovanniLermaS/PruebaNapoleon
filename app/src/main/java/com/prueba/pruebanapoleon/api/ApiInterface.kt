package com.prueba.pruebanapoleon.api

import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.db.model.User
import com.prueba.pruebanapoleon.di.rest.Endpoints.GET_POSTS
import com.prueba.pruebanapoleon.di.rest.Endpoints.GET_USERS
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(GET_POSTS)
    fun getPosts(): Single<Response<ArrayList<Post>>>

    @GET(GET_USERS)
    fun getUsers(): Single<Response<ArrayList<User>>>

}