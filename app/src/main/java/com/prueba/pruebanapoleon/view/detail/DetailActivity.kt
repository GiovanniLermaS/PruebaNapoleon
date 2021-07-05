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
        injectClass()
        setDataBinding()
    }

    private fun setDataBinding() {
        val activityDetailBinding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        activityDetailBinding.setVariable(BR.detailActivity, this)
        activityDetailBinding.executePendingBindings()
        consumeServiceUsers(activityDetailBinding)
    }

    private fun injectClass() {
        (applicationContext as MyApplication).getComponent()?.inject(this)

        detailActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailActivityViewModel::class.java)
    }

    private fun consumeServiceUsers(activityDetailBinding: ActivityDetailBinding) {
        detailActivityViewModel?.getUsersV({ errorMessage ->
            pbMainActivity.visibility = View.GONE
            errorMessage.let { Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show() }
        }, { users ->
            val user = users.filter { it.id == post.userId }[0]
            activityDetailBinding.name.text =
                StringBuilder().append(user.name).append(" ").append(user.username)
            activityDetailBinding.phone.text = user.phone
            activityDetailBinding.email.text = user.email
        })
    }
}