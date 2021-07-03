package com.prueba.pruebanapoleon.view.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prueba.pruebanapoleon.databinding.PostListItemBinding
import com.prueba.pruebanapoleon.db.model.Post
import com.prueba.pruebanapoleon.view.main.interfaces.OnClickPost

class MainRecyclerViewAdapter(private val onClickPost: OnClickPost) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>() {

    private var posts = ArrayList<Post>()
    private var temporalPosts = ArrayList<Post>()

    fun setPostsData(posts: ArrayList<Post>) {
        this.posts = posts
        this.temporalPosts = posts
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PostListItemBinding.inflate(layoutInflater)
        return MyViewHolder(binding, onClickPost)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class MyViewHolder(
        private val binding: PostListItemBinding,
        private val onClickPost: OnClickPost
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.postData = post
            binding.executePendingBindings()
        }
    }
}