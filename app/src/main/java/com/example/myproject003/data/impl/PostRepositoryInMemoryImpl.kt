package com.example.myproject003.data.impl

import androidx.lifecycle.MutableLiveData
import com.example.myproject003.R
import com.example.myproject003.data.Post
import com.example.myproject003.data.PostRepository

class PostRepositoryInMemoryImpl: PostRepository {

    override val data = MutableLiveData(
        Post(
            id = 0L,
            author = "Max",
            content = "Event",
            published = "04.08.2022",
            likeByMe = false,
            likeCount = 189,
            repostByMe = false,
            repostCount = 3
        )
    )

    override fun like() {

        var quantityLikes = data.value?.likeCount

        val currentPost = checkNotNull(data.value) {
            "data value should not be null"
        }
        val likedPost = currentPost.copy(
            likeByMe = !currentPost.likeByMe,
            likeCount = if (currentPost.likeByMe == true) {quantityLikes!!-1} else {quantityLikes!!+1}
        )
        data.value = likedPost
    }

    override fun repost() {
        var quantityReposts = data.value?.repostCount

        val currentPost = checkNotNull(data.value) {
            "data value should not be null"
        }
        val repostPost = currentPost.copy(
            repostByMe = !currentPost.repostByMe,
            repostCount = if (currentPost.repostByMe == true) {quantityReposts!!-1} else {quantityReposts!!+1}
        )
        data.value = repostPost
    }

}