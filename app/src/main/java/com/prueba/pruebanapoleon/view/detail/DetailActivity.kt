package com.prueba.pruebanapoleon.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.prueba.pruebanapoleon.BR
import com.prueba.pruebanapoleon.R
import com.prueba.pruebanapoleon.application.MyApplication
import com.prueba.pruebanapoleon.databinding.ActivityDetailBinding
import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.utils.POST
import com.prueba.pruebanapoleon.utils.ViewModelFactory
import com.prueba.pruebanapoleon.viewmodel.DetailActivityViewModel
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var detailActivityViewModel: DetailActivityViewModel? = null

    val post by lazy { intent.getSerializableExtra(POST) as Post }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        initViewModel()
        consumeServiceUsers()
    }

    private fun setDataBinding() {
        val activityDetailBinding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        activityDetailBinding.setVariable(BR.detailActivity, this)
        activityDetailBinding.executePendingBindings()
    }

    private fun initViewModel() {
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)
    }

    private fun consumeServiceUsers() {
        detailActivityViewModel?.getUsersV({ errorMessage ->
            pbMainActivity.visibility = View.GONE
            errorMessage.let { Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show() }
        }, { users ->
            users[0]
        })
    }
}