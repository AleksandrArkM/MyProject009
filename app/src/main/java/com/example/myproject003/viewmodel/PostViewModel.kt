package com.example.myproject003.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myproject003.data.PostRepository
import com.example.myproject003.data.impl.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    val data get() = repository.data

    fun onLikeClicked() = repository.like()
    fun onRepostClicked() = repository.repost()
}