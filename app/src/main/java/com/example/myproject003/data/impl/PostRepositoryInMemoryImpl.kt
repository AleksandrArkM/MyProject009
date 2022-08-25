package com.example.myproject003.data.impl

import androidx.lifecycle.MutableLiveData
import com.example.myproject003.data.Post
import com.example.myproject003.data.PostRepository

class PostRepositoryInMemoryImpl: PostRepository {

    private val posts get() = checkNotNull(data.value) {
        "Should not be null"
    }

    override val data = MutableLiveData(
        List(10) { index ->
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
    )

    override fun like(postId: Long) {

        data.value = posts.map {
            var quantityLikes = it.likeCount

            if (it.id == postId) it.copy(
                likeByMe = !it.likeByMe,
                        likeCount = if (it.likeByMe == true) {quantityLikes!!-1} else {quantityLikes!!+1}
            ) else it
        }
    }

    override fun repost(postId: Long) {

        data.value = posts.map {
            if (it.id == postId) it.copy(
                repostCount = it.repostCount + 1
            )
            else it
        }
    }

}