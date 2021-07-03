package com.prueba.pruebanapoleon.viewmodel

import androidx.lifecycle.ViewModel
import com.prueba.pruebanapoleon.db.model.User
import com.prueba.pruebanapoleon.repository.DetailActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(private val detailActivityRepository: DetailActivityRepository) :
    ViewModel() {

    fun getUsersV(callbackError: (String) -> Unit, callbackSuccess: (ArrayList<User>) -> Unit) {
        detailActivityRepository.getUsersR().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ArrayList<User>>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    e.message?.let { callbackError(it) }
                }

                override fun onSuccess(posts: Response<ArrayList<User>>) {
                    callbackSuccess(posts.body()!!)
                }
            })
    }
}
