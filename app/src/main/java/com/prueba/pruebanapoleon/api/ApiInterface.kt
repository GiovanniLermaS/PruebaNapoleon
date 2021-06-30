package com.prueba.pruebanapoleon.api

import com.prueba.pruebanapoleon.di.rest.Endpoints.GET_POSTS
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(GET_POSTS)
    fun getPosts(): Single<Response<ArrayList<Post>>>

}