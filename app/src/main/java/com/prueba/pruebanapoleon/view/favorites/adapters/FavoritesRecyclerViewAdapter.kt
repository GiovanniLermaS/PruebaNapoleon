package com.prueba.pruebanapoleon.view.favorites.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prueba.pruebanapoleon.databinding.PostFavoriteItemBinding
import com.prueba.pruebanapoleon.db.model.Post

class FavoritesRecyclerViewAdapter() :
    RecyclerView.Adapter<FavoritesRecyclerViewAdapter.MyViewHolder>() {

    private var posts = ArrayList<Post>()

    fun setPostsData(posts: ArrayList<Post>) {
        this.posts = posts
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostFavoriteItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class MyViewHolder(
        private val binding: PostFavoriteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.postData = post
            binding.executePendingBindings()
        }
    }
}