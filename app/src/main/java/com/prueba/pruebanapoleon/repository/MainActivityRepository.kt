package com.prueba.pruebanapoleon.repository

import com.prueba.pruebanapoleon.api.ApiInterface
import com.prueba.pruebanapoleon.db.model.Post
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MainActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getPostsR(): Single<Response<ArrayList<Post>>> {
        return apiInterface.getPosts()
    }
}