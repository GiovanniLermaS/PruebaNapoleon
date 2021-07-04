package com.prueba.pruebanapoleon.view.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.prueba.pruebanapoleon.BR
import com.prueba.pruebanapoleon.R
import com.prueba.pruebanapoleon.databinding.ActivityFavoritesBinding
import com.prueba.pruebanapoleon.db.AppDatabase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        getFavorites()
    }

    private fun setDataBinding() {
        val activityMainBinding =
                DataBindingUtil.setContentView<ActivityFavoritesBinding>(this, R.layout.activity_favorites)
        activityMainBinding.setVariable(BR.favoritesActivity, this)
        activityMainBinding.executePendingBindings()
    }

    private fun getFavorites() {
        lifecycleScope.launch {
            appDatabase?.postDao()?.getFavoritesPosts()
        }
    }
}