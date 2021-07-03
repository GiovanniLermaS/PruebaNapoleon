package com.prueba.pruebanapoleon.repository

import com.prueba.pruebanapoleon.api.ApiInterface
import com.prueba.pruebanapoleon.db.model.User
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DetailActivityRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getUsersR(): Single<Response<ArrayList<User>>> {
        return apiInterface.getUsers()
    }
}