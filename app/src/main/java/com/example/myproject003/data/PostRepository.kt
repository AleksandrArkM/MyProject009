package com.example.myproject003.data

import androidx.lifecycle.LiveData

interface PostRepository {
    val data : LiveData<Post>
    fun like()
    fun repost()
}