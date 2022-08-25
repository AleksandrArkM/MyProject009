package com.example.myproject003.data.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject003.R
import com.example.myproject003.data.Post
import com.example.myproject003.databinding.PostItemBinding
import com.example.myproject003.viewmodel.CountCheck
import kotlin.properties.Delegates

internal class PostAdapter (
    private val onLikeClicked: (Post) -> Unit,
    private val onRepostClicked: (Post) -> Unit
        ): ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflator,parent,false)
        return ViewHolder(binding, onLikeClicked, onRepostClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class ViewHolder(
        private val binding: PostItemBinding,
        private val onLikeClicked: (Post) -> Unit,
        private val onRepostClicked: (Post) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post

        init {
            binding.likesButton.setOnClickListener {onLikeClicked(post)}
            binding.shareButton.setOnClickListener {onRepostClicked(post)}
        }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                headTextPost.text = post.content
                data.text = post.published
                likesNumber.text = CountCheck(post.likeCount).outputNumber
                likesButton.setImageResource(getLikeIconResId(post.likeByMe))
                sharesCount.text = CountCheck(post.repostCount).outputNumber
            }
        }

        @DrawableRes
        private fun getLikeIconResId(liked: Boolean) =
            if (liked)
            {R.drawable.ic_liked_24
            } else
            {R.drawable.ic_baseline_favorite_24}
    }


    private object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
    }
}