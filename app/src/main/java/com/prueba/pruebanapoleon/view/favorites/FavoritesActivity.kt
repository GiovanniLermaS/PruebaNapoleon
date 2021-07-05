package com.prueba.pruebanapoleon.view.favorites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.prueba.pruebanapoleon.BR
import com.prueba.pruebanapoleon.R
import com.prueba.pruebanapoleon.application.MyApplication
import com.prueba.pruebanapoleon.databinding.ActivityFavoritesBinding
import com.prueba.pruebanapoleon.db.AppDatabase
import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.view.favorites.adapters.FavoritesRecyclerViewAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set

    private var recyclerViewAdapter = FavoritesRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDataBinding()
        injectClass()
        setAdapterData()
    }

    private fun setDataBinding() {
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityFavoritesBinding>(
                this,
                R.layout.activity_favorites
            )
        activityMainBinding.setVariable(BR.favoritesActivity, this)
        activityMainBinding.executePendingBindings()
    }

    private fun injectClass() {
        (applicationContext as MyApplication).getComponent()?.inject(this)
    }

    fun getAdapter(): FavoritesRecyclerViewAdapter {
        return recyclerViewAdapter
    }

    private fun setAdapterData() {
        lifecycleScope.launch {
            val favorites = appDatabase?.postDao()?.getFavoritesPosts() as ArrayList<Post>?
            if (favorites != null && favorites.isNotEmpty()) {
                recyclerViewAdapter.setPostsData(favorites)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }
}