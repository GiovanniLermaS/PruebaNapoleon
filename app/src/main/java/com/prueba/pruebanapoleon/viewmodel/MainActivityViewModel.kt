package com.prueba.pruebanapoleon.viewmodel

import androidx.lifecycle.ViewModel
import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.repository.MainActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityRepository: MainActivityRepository) : ViewModel() {

    fun getPostsV(callbackError: (String) -> Unit, callbackSuccess: (ArrayList<Post>) -> Unit) {
        mainActivityRepository.getPostsR().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<ArrayList<Post>>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    e.message?.let { callbackError(it) }
                }

                override fun onSuccess(posts: Response<ArrayList<Post>>) {
                    callbackSuccess(posts.body()!!)
                }
            })
    }
}
