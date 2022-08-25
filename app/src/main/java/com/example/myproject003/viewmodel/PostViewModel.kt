package com.example.myproject003.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myproject003.data.Post
import com.example.myproject003.data.PostRepository
import com.example.myproject003.data.impl.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data = repository.getAll()

    fun onLikeClicked(post: Post) = repository.like(post.id)
    fun onRepostClicked(post: Post) = repository.repost(post.id)
}