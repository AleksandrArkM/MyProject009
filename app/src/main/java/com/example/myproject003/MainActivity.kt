package com.example.myproject003

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.myproject003.data.Post
import com.example.myproject003.databinding.ActivityMainBinding
import com.example.myproject003.viewmodel.*
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) {post ->
            binding.render(post)
        }

        binding.likesButton.setOnClickListener {
            viewModel.onLikeClicked()
        }

        binding.shareButton.setOnClickListener {
            viewModel.onRepostClicked()
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        headTextPost.text = post.content
        data.text = post.published
        likesNumber.text = CountCheck(post.likeCount).outputNumber
        likesButton.setImageResource(getLikeIconResId(post.likeByMe))
        sharesCount.text = CountCheck(post.repostCount).outputNumber
    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked)
            {R.drawable.ic_liked_24
        } else
            {R.drawable.ic_baseline_favorite_24}

}
