package com.prueba.pruebanapoleon.view.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.prueba.pruebanapoleon.BR
import com.prueba.pruebanapoleon.R
import com.prueba.pruebanapoleon.application.MyApplication
import com.prueba.pruebanapoleon.databinding.ActivityMainBinding
import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.utils.POST
import com.prueba.pruebanapoleon.utils.ViewModelFactory
import com.prueba.pruebanapoleon.view.detail.DetailActivity
import com.prueba.pruebanapoleon.view.main.adapters.MainRecyclerViewAdapter
import com.prueba.pruebanapoleon.view.main.interfaces.OnClickPost
import com.prueba.pruebanapoleon.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnClickPost {

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    private var recyclerViewAdapter = MainRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        initViewModel()
        consumeServicePosts()
    }

    override fun goDetailActivity(post: Post) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(POST, post)
        startActivity(intent)
    }

    private fun setDataBinding() {
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.setVariable(BR.mainActivity, this)
        activityMainBinding.executePendingBindings()
    }

    private fun initViewModel() {
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

    private fun consumeServicePosts() {
        mainActivityViewModel?.getPostsV({ errorMessage ->
            pbMainActivity.visibility = View.GONE
            errorMessage.let { Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show() }
        }, { posts ->
            setAdapterData(posts)
            pbMainActivity.visibility = View.GONE
        })
    }

    fun getAdapter(): MainRecyclerViewAdapter {
        return recyclerViewAdapter
    }

    private fun setAdapterData(posts: ArrayList<Post>) {
        var count = 0
        val temporalPosts = ArrayList<Post>()
        posts.forEach { _ ->
            posts[count].isColor = count < 20
            temporalPosts.add(posts[count])
            count++
        }
        recyclerViewAdapter.setPostsData(temporalPosts)
        recyclerViewAdapter.notifyDataSetChanged()
    }
}