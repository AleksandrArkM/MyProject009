package com.example.myproject003.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myproject003.data.Post
import com.example.myproject003.data.PostRepository

class PostRepositoryInMemoryImpl: PostRepository {

    private var posts = List(10) { index ->
        Post(
            id = index + 1L,
            author = "Netology",
            content = "Random post $index",
            published = "04.08.2022",
            likeByMe = false,
            likeCount = 165,
            repostByMe = false,
            repostCount = 3
        )
    }
    val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun like(postId: Long) {
        data.value = posts.map { it ->
            if (it.id != postId) it
            else it.copy(likeByMe = ! it.likeByMe)
        }
        posts = posts.map { post ->
            if (post.id == postId) {
                if (post.likeByMe) post.copy(likeCount = post.likeCount - 1)
                else post.copy(likeCount = post.likeCount + 1)
            } else post
        }
        data.value = posts
    }

    override fun repost(postId: Long) {
        posts = posts.map { post ->
            if (post.id == postId) {
                post.copy(repostCount = post.repostCount + 1)
            } else post
        }
        data.value = posts
    }

}